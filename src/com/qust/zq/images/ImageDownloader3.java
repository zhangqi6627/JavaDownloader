package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageDownloader3 {
	// http://www.youzi4.cc/mm/20648/20648_1.html
	// http://www.47843.com/
	public static final String DOWNLOAD_PATH = Constants.DOWNLOAD_PATH + "/youzi4/";
	public static int downloadCount = 0;
	public final static String PAGE_URL_FORMAT = "http://www.youzi4.cc/mm/%d/%d_%d.html";
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 20000; i++) {
			long startTime1 = System.currentTimeMillis();
			for (int k = 1; k < 100; k++) {
				String pageUrl = String.format(PAGE_URL_FORMAT, i, i, k);
				try {
					boolean downSuc = downloadImageUrlFromPage(pageUrl, i, k);
					if (!downSuc) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			long stopTime1 = System.currentTimeMillis();
			System.out.println("spend1 : " + (stopTime1 - startTime1) / 1000 + " secs");
		}
		/*
		 * for(int i = 0 ;i < 10;i++){ new Thread(new ImageDownloadThread(i)).start(); }
		 */
		long stopTime = System.currentTimeMillis();
		System.out.println("total spend2 : " + (stopTime - startTime) / 1000);
	}
	static class ImageDownloadThread implements Runnable {
		private int threadIndex;
		public ImageDownloadThread(int threadIndex) {
			this.threadIndex = threadIndex;
		}
		@Override
		public void run() {
			for (int i = 2044; i > 0; i--) {
				long startTime1 = System.currentTimeMillis();
				for (int k = 1; k < 100; k++) {
					int downloadIndex = i * 10 + threadIndex;
					String pageUrl = String.format(PAGE_URL_FORMAT, downloadIndex, downloadIndex, k);
					try {
						boolean downSuc = downloadImageUrlFromPage(pageUrl, downloadIndex, k);
						if (!downSuc) {
							continue;
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
				long stopTime1 = System.currentTimeMillis();
				System.out.println("thread" + threadIndex + " spend1 : " + (stopTime1 - startTime1) / 1000 + " secs");
			}
		}
	}
	private static boolean downloadImageUrlFromPage(String pageUrl, int folderIndex, int imageIndex) throws Exception {
		Document doc = Jsoup.connect(pageUrl).get();
		Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
		String imageUrl = imageShowElement.attr("src");
		System.out.println("downloading imageUrl:" + imageUrl);
		String imageFolderName = folderIndex + "_" + imageShowElement.attr("alt");
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		String imageName = String.format(folderIndex + "_%03d", imageIndex);
		String subFix = imageUrl.substring(imageUrl.lastIndexOf("."));
		imageName += subFix;
		File imageFile = new File(DOWNLOAD_PATH + imageFolderName + "/" + imageName);
		if (imageFile.exists()) {
			return false;
		}
		downloadImage(imageUrl, imageFolderName, imageName);
		return true;
	}
	public static void downloadImage(String urlString, String folderName, String filename) throws Exception {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		// con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		// con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
		// con.setRequestProperty("Cache-Control", "max-age=0");
		// con.setRequestProperty("Connection", "keep-alive");
		// con.setRequestProperty("Cookie", "BUSER=14926814c9082dbea329f1e363ca86df");
		con.setRequestProperty("Referer", "http://www.youzi4.cc/mm/2/2_1.html");
		// con.setRequestProperty("Host", "www.youzi4.cc");
		// con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		con.setConnectTimeout(5 * 1000);
		con.connect();
		InputStream is = con.getInputStream();
		byte[] bs = new byte[1024];
		int len;
		File sf = new File(DOWNLOAD_PATH + folderName);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		downloadCount++;
		os.close();
		is.close();
	}
}
