package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class ImageDownloader5 {
	// https://www.meitulu.com/item/3_2.html
	// https://www.meitulu.com/img.html?img=https://mtl.ttsqgs.com/images/img/3/11.jpg
	public static final String DOWNLOAD_PATH = Constants.DOWNLOAD_PATH + "/meitulu/";
	public static int downloadCount = 0;
	public final static String PAGE_URL_FORMAT = "https://mtl.ttsqgs.com/images/img/%d/%d.jpg";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 15836; i++) {
			long startTime1 = System.currentTimeMillis();
			for (int k = 1; k < 200; k++) {
				String pageUrl = String.format(PAGE_URL_FORMAT, i, k);
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
		// Document doc = Jsoup.connect(pageUrl).headers(header).get();
		// Element contentElement = doc.getElementsByClass("content").get(0);
		// System.out.println(contentElement);
		// Element imageShowElement = contentElement.getElementsByTag("img").get(0);
		// String imageUrl = imageShowElement.attr("src");
		String folderName = String.format("%05d", folderIndex);
		if (folderName.lastIndexOf(" ") != -1) {
			folderName = folderName.substring(0, folderName.lastIndexOf(" "));
			// System.out.println(folderName);
		}
		System.out.println("downloading imageUrl:" + pageUrl + " folderName:" + folderName);
		String imageFolderName = String.format("%05d", folderIndex);
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		// String imageName = String.format(folderIndex + "_%03d", imageIndex);
		String imageName = String.format("%05d_%03d", folderIndex, imageIndex);
		// String subFix = imageUrl.substring(imageUrl.lastIndexOf("."));
		// imageName += subFix;
		File imageFile = new File(DOWNLOAD_PATH + imageName);
		if (imageFile.exists()) {
			return false;
			
		}
		downloadImage(pageUrl, imageFolderName, imageName + ".jpg");
		return true;
	}

	public static void downloadImage(String urlString, String folderName, String filename) throws Exception {
		URL url = new URL(urlString);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestProperty(
				"Cookie",
				"UM_distinctid=1654e424357610-091dd6d937f02e-3464790b-fa000-1654e424358148; CNZZDATA1255357127=443251054-1534616107-https%253A%252F%252Fwww.google.com.hk%252F%7C1534616107; Hm_lvt_1e2b00875d672f10b4eee3965366013f=1534616290; CNZZDATA1255487232=1541027707-1534610964-%7C1534616396");
		con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
		con.setRequestProperty("Referer", "https://www.meitulu.com/img.html?img=https://mtl.ttsqgs.com/images/img/3/12.jpg");

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
