package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageDownloader8 {
	// http://zhyuge.com/picture/detail?pictureId=1
	public static final String DOWNLOAD_PATH = Constants.DOWNLOAD_PATH + "/zhyuge/";
	public static int downloadCount = 0;
	public final static String PAGE_URL_FORMAT = "http://zhyuge.com/picture/detail?pictureId=%d";
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 20000; i++) {
			long startTime1 = System.currentTimeMillis();
			String pageUrl = String.format(PAGE_URL_FORMAT, i);
			try {
				boolean downSuc = downloadImageUrlFromPage(pageUrl, i);
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
		long stopTime = System.currentTimeMillis();
		System.out.println("total spend2 : " + (stopTime - startTime) / 1000);
	}
	private static boolean downloadImageUrlFromPage(String pageUrl, int folderIndex) throws Exception {
		Map<String, String> header = new HashMap<String, String>();

		Document doc = Jsoup.connect(pageUrl).headers(header).get();
		Element contentElement = doc.getElementsByClass("content").get(0);
		// System.out.println(contentElement);
		Element imageShowElement = contentElement.getElementsByTag("img").get(0);
		String imageUrl = imageShowElement.attr("src");
		String folderName = imageShowElement.attr("alt");
		if (folderName.lastIndexOf(" ") != -1) {
			folderName = folderName.substring(0, folderName.lastIndexOf(" "));
			// System.out.println(folderName);
		}
		System.out.println("downloading imageUrl:" + imageUrl + " folderName:" + folderName);
		String imageFolderName = String.format("%05d", folderIndex) + "_" + folderName;
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		String imageName = String.format(folderIndex + "_%03d", 1);
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
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        con.setRequestProperty("Connection", "keep-alive");
        //con.setRequestProperty("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
        con.setRequestProperty("Host", "www.mmjpg.com");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
        con.setRequestProperty("Referer", "http://www.mmjpg.com/mm/2/2");
        
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
