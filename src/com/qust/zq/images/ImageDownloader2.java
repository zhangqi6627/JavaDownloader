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
import org.jsoup.select.Elements;

public class ImageDownloader2 {
	// 8594
	public static final String DOWNLOAD_PATH = "/Users/zhangqi/zq/image2/";
	public static int downloadCount = 0;
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String pageUrlFormat = "http://mf94.xyz/img_%s.html";
		for (int i = 1; i <= 8594; i++) {
			String pageUrl = String.format(pageUrlFormat, i);
			downloadPageImages(pageUrl, i);
		}
		long stopTime = System.currentTimeMillis();
	}
	public static void downloadPageImages(String pageUrl, int index) {
		try {
			Document doc = Jsoup.connect(pageUrl).get();
			String title = doc.getElementsByTag("h4").text();
			title = String.format("%05d", index) + "_" + title;
			// System.out.println(title);
			File sf = new File(DOWNLOAD_PATH + title);
			if (sf.exists()) {
				return;
			}
			Elements elements = doc.getElementsByAttributeValue("class", "img lazy");
			for (Element element : elements) {
				String imageUrl = element.attr("data-original");
				String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
				downloadImage(imageUrl, title, String.format("%05d", index) + "_" + imageName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void downloadImage(String urlString, String folderName, String filename) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		con.setConnectTimeout(5 * 1000);
		InputStream is = con.getInputStream();
		System.out.println("downloading:" + filename);
		byte[] bs = new byte[1024];
		int len;
		File sf = new File(DOWNLOAD_PATH);
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
