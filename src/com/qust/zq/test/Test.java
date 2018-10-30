package com.qust.zq.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public static final String DOWNLOAD_PATH = "/home/zq/MyFavorites/girlsky/";

	public static void main(String[] args) {
		int[] posAry = new int[]{5, 4, 6, 11, 11, 12, 14, 5, 7, 5, 15, 4, 6, 10, 3, 7, 1, 13, 10, 0, 8};
		char[] websiteAry = new char[]{'3', 'o', '1', 'w', 'h', 't', 'w', 'm', '6', 'c', ':', 'n', 'p', '/', '.', 'w'};
		System.out.print("程序猿专属解忧网站：");
		for(int i=0;i<posAry.length;i++){
			System.out.print(websiteAry[i%2==0 ? posAry[i]-1 : posAry[i]+1]);
		}
		
		// http://www.girlsky.cn/article/
		/*
		String urlFormat = "http://www.girlsky.cn/article/%d/";
		long beginTime = System.currentTimeMillis();
		for (int i = 29045; i < 30000; i++) {
			String urlStr = String.format(urlFormat, i);
			try {
				Element element = Jsoup.connect(urlStr).get().getElementById("picture").getElementsByTag("img").get(0);
				String imageUrl = element.attr("src");
				downloadImage(imageUrl, "" + i, "1.jpg");
				System.out.println(i + " imageUrl:" + imageUrl + "     spendTime:" + ((System.currentTimeMillis() - beginTime) / 1000) + " s");
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(i + ":" + e.getMessage());
				continue;
			}

			for (int k = 1; k < 100; k++) {
				try {
					Elements elements = Jsoup.connect(urlStr + k).get().getElementById("picture").getElementsByTag("img");
					if (elements.size() > 0) {
						Element element = elements.get(0);
						String imageUrl = element.attr("src");
						downloadImage(imageUrl, "" + i, k + ".jpg");
						System.out.println(i + " imageUrl:" + imageUrl);
					} else {
						break;
					}
				} catch (Exception e) {
					break;
				}
			}
		}
		*/
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
		os.close();
		is.close();
	}
}
