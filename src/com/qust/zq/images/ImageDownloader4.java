package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImageDownloader4 {
	// 有反爬虫机制
	// http://www.mmjpg.com/mm/1
	public static final String DOWNLOAD_PATH = "/home/zq/MyFavorites/mmjpg/";
	public static int downloadCount = 0;
	public final static String PAGE_URL_FORMAT = "http://www.mmjpg.com/mm/%d/%d";
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		//
		for (int i = 1; i < 20000; i++) {
			long startTime1 = System.currentTimeMillis();
			AlbumBean albumBean = new AlbumBean();
			for (int k = 1; k < 100; k++) {
				String pageUrl = String.format(PAGE_URL_FORMAT, i, k);
				try {
					boolean downSuc = downloadImageUrlFromPage(albumBean, pageUrl, i, k);
					if (!downSuc) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					JsonTools.createJsonFile(albumBean.toJson().toString(), "/home/zq/MyFavorites/", String.format("%05d", i));
					break;
				}
			}
			long stopTime1 = System.currentTimeMillis();
			System.out.println("spend1 : " + (stopTime1 - startTime1) / 1000 + " secs");
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("total spend2 : " + (stopTime - startTime) / 1000);
	}
	private static boolean downloadImageUrlFromPage(AlbumBean albumBean, String pageUrl, int folderIndex, int imageIndex) throws Exception {
		Document doc = Jsoup.connect(pageUrl).get();
		Element contentElement = doc.getElementsByClass("content").get(0);
		Element imageShowElement = contentElement.getElementsByTag("img").get(0);
		String imageUrl = imageShowElement.attr("src");
		String folderName = imageShowElement.attr("alt");
		if (folderName.lastIndexOf(" ") != -1) {
			folderName = folderName.substring(0, folderName.lastIndexOf(" "));
		}
		albumBean.setWebIndex(4);
		albumBean.setPageIndex(folderIndex);
		albumBean.setTitle(folderName);
		albumBean.setAlbumImage(imageUrl);
		albumBean.addImage(imageUrl);
		System.out.println("downloading imageUrl:" + imageUrl + " folderName:" + folderName);
		String imageFolderName = String.format("%05d", folderIndex) + "_" + folderName;
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		String imageName = String.format("%05d_%03d", folderIndex, imageIndex);
		String subFix = imageUrl.substring(imageUrl.lastIndexOf("."));
		imageName += subFix;
		File imageFile = new File(DOWNLOAD_PATH + imageFolderName + "/" + imageName);
		if (imageFile.exists()) {
			return false;
		}
		//downloadImage(imageUrl, imageFolderName, imageName);
		return true;
	}
	public static void downloadImage(String urlString, String folderName, String filename) throws Exception {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Host", "fm.shiyunjj.com");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
        con.setRequestProperty("Referer", "http://www.mmjpg.com/mm/108/1");
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
