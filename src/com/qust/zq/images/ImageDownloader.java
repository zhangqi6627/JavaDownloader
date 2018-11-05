package com.qust.zq.images;

import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageDownloader {
	// 8594
	public static final String DOWNLOAD_PATH = Constants.DOWNLOAD_PATH + "/zp2006/";
	//public static int downloadCount = 0;
	public static void main(String[] args) {
		//http://mf94.xyz/ 这个就是zp2006
		String pageUrlFormat = "http://mf94.xyz/img_%s.html";
		for (int i = 1; i <= 8700; i++) {
			String pageUrl = String.format(pageUrlFormat, i);
			downloadPageImages(pageUrl, i);
		}
		//
	}
	public static void downloadPageImages(String pageUrl, int index) {
		try {
			Document doc = Jsoup.connect(pageUrl).get();
			String title = doc.getElementsByTag("h4").text();
			title = String.format("%05d", index) + "_" + title;
			File sf = new File(DOWNLOAD_PATH + title);
			if (sf.exists()) {
				return;
			}
			Elements elements = doc.getElementsByAttributeValue("class", "img lazy");
			for (Element element : elements) {
				String imageUrl = element.attr("data-original");
				String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
				ImageUtils.downloadImage(1, imageUrl, DOWNLOAD_PATH, title, imageName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
