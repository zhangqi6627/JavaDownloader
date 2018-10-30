package com.qust.zq.novels;

import java.io.File;
import java.io.FileOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NovelDownloader {
	public static final String DOWNLOAD_PATH = "/Users/zhangqi/zq/novels/";
	public static String urlFormat = "http://mayi6.top/t_%d.html";
	//http://www.jlsdmy.com/sn_1.html
	public static void main(String[] args) {
		for (int i = 0; i <= 2100; i++) {
			downloadNovel(String.format(urlFormat, i), i);
		}
	}
	public static void downloadNovel(String pageUrl, int index) {
		try {
			
			Document doc = Jsoup.connect(pageUrl).get();
			Elements elements = doc.getElementsByClass("breadcrumb-item");
			Element element = elements.get(1);
			String type = element.getElementsByTag("a").text();
			//System.out.println(type);
			String title = String.format("%05d", index) + "_" + type + "_" + doc.getElementsByTag("h3").text();
			title = title.replace(" ", "");
			System.err.println("downloading " + title);
			String content = doc.getElementsByClass("shortNovelContent").text().replace("[br]", "\n");
			File novelFile = new File(DOWNLOAD_PATH + title + ".txt");
			if (novelFile.exists()) {
				return;
			}
			FileOutputStream fileOutputStream = new FileOutputStream(novelFile);
			fileOutputStream.write(content.getBytes());
			fileOutputStream.close();
			// System.out.println(title);
			// System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
