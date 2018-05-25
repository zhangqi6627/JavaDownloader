package com.qust.zq.images;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.IOUtils;

public class M3U8Downloader {
	// 1796-1863
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 1796; i <= 1863; i++) {
			// System.out.println(movieName);
			try {
				downloadM3U8(i);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(executor != null){
			executor.shutdown();
		}
		long stopTime = System.currentTimeMillis();
		System.out.println((stopTime - startTime) / 1000 + " secs");
	}
	public static void downloadM3U8(int index) throws IOException, InterruptedException {
		String movieName = "w" + index;
		String url = "http://videosrc2017.xyz/2018/05/" + movieName + "/" + movieName + ".m3u8?sign=c6e9f63b2d5aa631043d284f58bb284e";
		String tofile = "/Users/zhangqi/zq/movies/" + movieName + "/" + movieName + ".ts";
		
		System.out.println("get index for: " + url);
		M3U8 m3u8 = parseIndex(url);
		float f = 0;
		for (M3U8Ts ts : m3u8.getTsList()) {
			f += ts.getSeconds();
		}
		System.out.println("movie length: " + ((int) f / 60) + "min" + (int) f % 60 + "sec");
		download(m3u8, tofile);
		//executor.shutdown();
		System.out.println("Wait for downloader...");
		while (!executor.isTerminated()) {
			Thread.sleep(100);
		}
		merge(m3u8, tofile);
		System.out.println("download completed for: " + tofile);
		String mToFile = "/Users/zhangqi/zq/movies/" + movieName + ".ts";
		FileUtils.copyFile(new File(tofile), new File(mToFile));
		System.out.println("copy file success");
		boolean delSuc = FileUtils.deleteDir(new File("/Users/zhangqi/zq/movies/" + movieName));
		System.out.println("delete folder " + delSuc);
		
	}
	public static void merge(M3U8 m3u8, String tofile) throws IOException {
		File file = new File(tofile);
		FileOutputStream fos = new FileOutputStream(file);
		for (M3U8Ts ts : m3u8.getTsList()) {
			IOUtils.copyLarge(new FileInputStream(new File(file.getParentFile(), ts.getFile())), fos);
		}
		fos.close();
	}
	private static ExecutorService executor = Executors.newFixedThreadPool(20);
	public static void download(final M3U8 m3u8, final String tofile) throws IOException {
		final File dir = new File(tofile).getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		} else if (dir.list().length > 0) {
			throw new IOException("tofile dir must be empty or not exists");
		}
		for (final M3U8Ts ts : m3u8.getTsList()) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("download " + (m3u8.getTsList().indexOf(ts) + 1) + "/" + m3u8.getTsList().size() + ": " + ts);
						FileOutputStream writer = new FileOutputStream(new File(dir, ts.getFile()));
						IOUtils.copyLarge(new URL(m3u8.getBasepath() + ts.getFile()).openStream(), writer);
						writer.close();
						System.out.println("download ok for: " + ts);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	static M3U8 parseIndex(String url) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		String basepath = url.substring(0, url.lastIndexOf("/") + 1);
		M3U8 ret = new M3U8();
		ret.setBasepath(basepath);
		String line;
		float seconds = 0;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#")) {
				if (line.startsWith("#EXTINF:")) {
					line = line.substring(8);
					if (line.endsWith(",")) {
						line = line.substring(0, line.length() - 1);
					}
					seconds = Float.parseFloat(line);
				}
				continue;
			}
			if (line.endsWith("m3u8")) {
				return parseIndex(basepath + line);
			}
			ret.addTs(new M3U8Ts(line, seconds));
			seconds = 0;
		}
		reader.close();
		return ret;
	}
	static class M3U8 {
		private String basepath;
		private List<M3U8Ts> tsList = new ArrayList<M3U8Ts>();
		public String getBasepath() {
			return basepath;
		}
		public void setBasepath(String basepath) {
			this.basepath = basepath;
		}
		public List<M3U8Ts> getTsList() {
			return tsList;
		}
		public void setTsList(List<M3U8Ts> tsList) {
			this.tsList = tsList;
		}
		public void addTs(M3U8Ts ts) {
			this.tsList.add(ts);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("basepath: " + basepath);
			for (M3U8Ts ts : tsList) {
				sb.append("\nts: " + ts);
			}
			return sb.toString();
		}
	}
	static class M3U8Ts {
		private String file;
		private float seconds;
		public M3U8Ts(String file, float seconds) {
			this.file = file;
			this.seconds = seconds;
		}
		public String getFile() {
			return file;
		}
		public void setFile(String file) {
			this.file = file;
		}
		public float getSeconds() {
			return seconds;
		}
		public void setSeconds(float seconds) {
			this.seconds = seconds;
		}
		@Override
		public String toString() {
			return file + " (" + seconds + "sec)";
		}
	}
}