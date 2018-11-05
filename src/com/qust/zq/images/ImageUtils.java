package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {
	public static void downloadImage(int webIndex, String urlString, String path, String folderName, String filename) throws Exception {
		InputStream is = getConnection(webIndex, urlString).getInputStream();
		byte[] bs = new byte[1024];
		int len;
		File sf = new File(path + folderName);
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
	private static HttpURLConnection getConnection(int webIndex, String urlString) throws IOException {
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
		switch (webIndex) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			// youzi4
			con.setRequestProperty("Host", "www.youzi4.cc");
			con.setRequestProperty("Referer", "http://www.youzi4.cc/mm/1/1_1.html");
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		}
		con.connect();
		return con;
	}
}
