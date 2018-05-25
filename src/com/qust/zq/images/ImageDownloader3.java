package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImageDownloader3 {
	// http://www.umei.cc/meinvtupian/meinvxiezhen/130851_1.htm
	// http://www.youzi4.cc/mm/20648/20648_1.html
	public static final String DOWNLOAD_PATH = "/home/zq/MyFavorites/beautiful/";
	public static int downloadCount = 0;
	public final static String PAGE_URL_FORMAT = "http://www.youzi4.cc/mm/%d/%d_%d.html";
	public final static int THREAD_COUNT = 10;

	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread(new ImageDownloadThread(i)).start();
		}
	}

	static class ImageDownloadThread implements Runnable {
		private int threadIndex;

		public ImageDownloadThread(int threadIndex) {
			this.threadIndex = threadIndex;
		}

		@Override
		public void run() {
			for (int i = 0; i < 22000 / THREAD_COUNT; i++) {
				int downloadIndex = i * THREAD_COUNT + threadIndex;
				try {
					boolean downSuc = downloadImageUrlFromPage(downloadIndex);
					if (!downSuc) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

	private static boolean downloadImageUrlFromPage(int folderIndex) throws Exception {
		boolean result = false;
		int imageIndex = 1;
		String pageUrl = String.format(PAGE_URL_FORMAT, folderIndex, folderIndex, imageIndex);
		Document doc = Jsoup.connect(pageUrl).get();
		Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
		String imageUrl = imageShowElement.attr("src");
		System.out.println("downloading imageUrl:" + imageUrl);
		String imageFolderName = String.format("%05d", folderIndex) + "_" + imageShowElement.attr("alt");
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		} else {
			// folder exist
			return false;
		}

		boolean isRunning = true;
		do {
			String imageName = String.format("%05d_%03d", folderIndex, imageIndex) + imageUrl.substring(imageUrl.lastIndexOf("."));
			System.out.println("imageIndex:" + imageIndex + "    imageName:" + imageName);
			File imageFile = new File(DOWNLOAD_PATH + imageFolderName + "/" + imageName);
			if (imageFile.exists()) {
				// image exist
				isRunning = false;
			} else {
				downloadImage(imageUrl, imageFolderName, imageName);
				imageIndex++;
				result = true;
			}
		} while (isRunning);

		return result;
	}

	public static void downloadImage(String urlString, String folderName, String filename) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		con.setConnectTimeout(5 * 1000);
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

	private static void main1() {
		for (int i = 0; i < 20000 / THREAD_COUNT; i++) {
			long startTime1 = System.currentTimeMillis();
			try {
				boolean downSuc = downloadImageUrlFromPage(i);
				if (!downSuc) {
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			long stopTime1 = System.currentTimeMillis();
			System.out.println("spend1 : " + (stopTime1 - startTime1) / 1000 + " secs");
		}
	}
}
