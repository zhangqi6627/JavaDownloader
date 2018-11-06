package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImageDownloaderB {
	// http://www.ili100.cn/a/1_3.html
	public static final String DOWNLOAD_PATH = WebSiteBean.DOWNLOAD_PATH + "/ili100/";
	public static int downloadCount = 0;
	public final static String PAGE_URL_FORMAT = "http://www.ili100.cn/a/%d_%d.html";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 20000; i++) {
			long startTime1 = System.currentTimeMillis();
			for (int k = 1; k < 100; k++) {
				String pageUrl = String.format(PAGE_URL_FORMAT, i, k);
				if (k == 1) {
					pageUrl = "http://www.ili100.cn/a/" + i + ".html";
				}
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
		long stopTime = System.currentTimeMillis();
		System.out.println("total spend2 : " + (stopTime - startTime) / 1000);
	}

	private static boolean downloadImageUrlFromPage(String pageUrl, int folderIndex, int imageIndex) throws Exception {
		Document doc = Jsoup.connect(pageUrl).get();
		String titleString = doc.getElementById("T-bt").ownText();
		if (titleString.indexOf("(") > 0) {
			titleString = titleString.substring(0, titleString.indexOf("("));
		}
		String imageUrl = "http://www.ili100.cn/" + doc.getElementById("bigimg").attr("src");
		// String imageUrl = doc.getElementsByClass("pic-large").get(0).attr("url");
		String imageFolderName = String.format("%05d_%s", folderIndex, titleString);
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		System.out.println(String.format("%05d_%03d_%s_%s", folderIndex, imageIndex, titleString, imageUrl));
		String imageName = String.format("%05d_%03d", folderIndex, imageIndex) + imageUrl.substring(imageUrl.lastIndexOf("."));
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
