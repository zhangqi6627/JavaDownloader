package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebSiteBean {
	private String pageUrlFormat;
	private int webIndex;
	private String refererUrl;
	private String downloadPath;
	private String webName;
	public final static String DOWNLOAD_PATH = "/Users/zhangqi/zq/images/";
	public static void main(String[] args) {
		boolean download3 = false;
		if (download3) {
			new WebSiteBean(new ImageProxy() {
				@Override
				public int getWebIndex() {
					return 3;
				}
				@Override
				public String getWebName() {
					return "youzi4";
				}
				@Override
				public String getRefererUrl() {
					return "http://www.youzi4.cc/mm/1/1_1.html";
				}
				@Override
				public String getPageUrlFormat() {
					return "http://www.youzi4.cc/mm/%d/%d_%d.html";
				}
				@Override
				public String getImageUrl(Document doc, int albumIndex, int imageIndex) {
					Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
					String imageUrl = imageShowElement.attr("src");
					return imageUrl;
				}
				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
					String albumName = imageShowElement.attr("alt");
					return albumName;
				}
			}).downloadAll();
		}
		//
		boolean download4 = false;
		if (download4) {
			new WebSiteBean(new ImageProxy() {
				@Override
				public String getWebName() {
					return "mmjpg";
				}
				@Override
				public int getWebIndex() {
					return 4;
				}
				@Override
				public String getRefererUrl() {
					return "http://www.mmjpg.com/mm/1/1";
				}
				@Override
				public String getPageUrlFormat() {
					return "http://www.mmjpg.com/mm/%d/%d";
				}
				@Override
				public String getImageUrl(Document doc, int albumInde, int imageIndex) {
					Element contentElement = doc.getElementsByClass("content").get(0);
					Element imageShowElement = contentElement.getElementsByTag("img").get(0);
					String imageUrl = imageShowElement.attr("src");
					return imageUrl;
				}
				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					Element contentElement = doc.getElementsByClass("content").get(0);
					Element imageShowElement = contentElement.getElementsByTag("img").get(0);
					String folderName = imageShowElement.attr("alt");
					if (folderName.lastIndexOf(" ") != -1) {
						folderName = folderName.substring(0, folderName.lastIndexOf(" "));
					}
					return folderName;
				}
			}).downloadAll();
		}
		//
		boolean download5 = false;
		new WebSiteBean(new ImageProxy() {
			@Override
			public String getWebName() {
				return "meitulu";
			}
			@Override
			public int getWebIndex() {
				return 5;
			}
			@Override
			public String getRefererUrl() {
				return "https://www.meitulu.com/item/3_2.html";
			}
			@Override
			public String getPageUrlFormat() {
				return "https://mtl.ttsqgs.com/images/img/%d/%d.jpg";
			}
			@Override
			public String getImageUrl(Document doc, int albumIndex, int imageIndex) {
				return String.format(getPageUrlFormat(), albumIndex, imageIndex);
			}
			@Override
			public String getAlbumName(Document doc, int albumIndex) {
				return String.format("%06d", albumIndex);
			}
		}).downloadAll();
	}
	public WebSiteBean(ImageProxy imageProxy) {
		this.imageProxy = imageProxy;
		this.webIndex = imageProxy.getWebIndex();
		this.pageUrlFormat = imageProxy.getPageUrlFormat();
		this.refererUrl = imageProxy.getRefererUrl();
		this.webName = imageProxy.getWebName();
		downloadPath = DOWNLOAD_PATH + webName + "/";
	}
	public void downloadAll() {
		for (int i = 1; i < 20000; i++) {
			AlbumBean albumBean = new AlbumBean();
			albumBean.setWebIndex(webIndex);
			for (int k = 1; k < 200; k++) {
				String pageUrl = String.format(pageUrlFormat, i, i, k);
				if (webIndex == 3) {
					pageUrl = String.format(pageUrlFormat, i, i, k);
				} else if (webIndex == 4) {
					pageUrl = String.format(pageUrlFormat, i, k);
				} else if (webIndex == 5) {
					pageUrl = String.format(pageUrlFormat, i, k);
				}
				try {
					boolean downSuc = downloadImageUrlFromPage(albumBean, pageUrl, i, k);
					if (!downSuc) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (albumBean.getImagesSize() > 0) {
						JsonTools.createJsonFile(albumBean.toJson().toString(), DOWNLOAD_PATH + "/" + webName + "/jsons", String.format("%06d", i));
					}
					break;
				}
			}
		}
	}
	public interface ImageProxy {
		public String getPageUrlFormat();
		public String getWebName();
		public int getWebIndex();
		public String getRefererUrl();
		public String getImageUrl(Document doc, int albumIndex, int imageIndex);
		public String getAlbumName(Document doc, int albumIndex);
	}
	private ImageProxy imageProxy;
	private boolean downloadImageUrlFromPage(AlbumBean albumBean, String pageUrl, int albumIndex, int imageIndex) throws Exception {
		Document doc = null;
		if (webIndex != 5) {
			doc = Jsoup.connect(pageUrl).get();
		}
		String imageUrl = imageProxy.getImageUrl(doc, albumIndex, imageIndex);
		String albumTitle = imageProxy.getAlbumName(doc, albumIndex);
		String folderName = albumIndex + "_" + albumTitle;
		File imageFolder = new File(downloadPath + folderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		albumBean.setPageIndex(albumIndex);
		albumBean.setTitle(albumTitle);
		albumBean.addImage(imageUrl);
		System.out.println(albumBean + "_" + imageUrl + "_" + albumBean.getImagesSize());
		String imageFileName = String.format("%06d_%03d", albumIndex, imageIndex);
		String subFix = imageUrl.substring(imageUrl.lastIndexOf("."));
		imageFileName += subFix;
		File imageFile = new File(imageFolder.getAbsolutePath() + "/" + imageFileName);
		if (imageFile.exists()) {
			return false;
		}
		downloadImage(imageUrl, imageFile);
		return true;
	}
	private void downloadImage(String urlString, File imageFile) throws Exception {
		InputStream is = getConnection(webIndex, urlString).getInputStream();
		byte[] bs = new byte[1024];
		int len;
		OutputStream os = new FileOutputStream(imageFile);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
	}
	private HttpURLConnection getConnection(int webIndex, String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Host", "www.meitulu.com");
		con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		con.setRequestProperty("Referer", refererUrl);
		con.setRequestProperty("Cookie", "UM_distinctid=1654e424357610-091dd6d937f02e-3464790b-fa000-1654e424358148; CNZZDATA1255357127=443251054-1534616107-https%253A%252F%252Fwww.google.com.hk%252F%7C1534616107; Hm_lvt_1e2b00875d672f10b4eee3965366013f=1534616290; CNZZDATA1255487232=1541027707-1534610964-%7C1534616396");
		con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setConnectTimeout(5 * 1000);
		con.connect();
		return con;
	}
}
