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
	public static String DOWNLOAD_PATH = "/Users/zhangqi/zq/images/";
	static {
		String os = System.getProperty("os.name");
		if (os.equalsIgnoreCase("Linux")) {
			DOWNLOAD_PATH = "/home/zq/MyFavorites/";
		} else if (os.equalsIgnoreCase("Mac")) {
			DOWNLOAD_PATH = "/Users/zhangqi/zq/images/";
		}
	}

	public static void main(String[] args) {
		//
		boolean download3 = false;
		if (download3) {
			new WebSiteBean(3, "youzi4", "http://www.youzi4.cc/mm/%d/%d_%d.html", "http://www.youzi4.cc/mm/1/1_1.html").downloadAll(new ImageProxy() {

				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
					String imageUrl = imageShowElement.attr("src");
					return imageUrl;
				}

				public String getAlbumName(Document doc, int albumIndex) {
					Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
					String albumName = imageShowElement.attr("alt");
					return albumName;
				}

				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, albumIndex, imageIndex);
				}
			});
		}
		//
		boolean download4 = false;
		if (download4) {
			new WebSiteBean(4, "mmjpg", "http://www.mmjpg.com/mm/%d/%d", "http://www.mmjpg.com/mm/1/1").downloadAll(new ImageProxy() {
				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumInde, int imageIndex) {
					Element contentElement = doc.getElementsByClass("content").get(0);
					Element imageShowElement = contentElement.getElementsByTag("img").get(0);
					String imageUrl = imageShowElement.attr("src");
					return imageUrl;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					Element contentElement = doc.getElementsByClass("content").get(0);
					Element imageShowElement = contentElement.getElementsByTag("img").get(0);
					String albumName = imageShowElement.attr("alt");
					if (albumName.lastIndexOf(" ") != -1) {
						albumName = albumName.substring(0, albumName.lastIndexOf(" "));
					}
					return albumName;
				}

				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}
			});
		}
		//
		boolean download5 = false;
		if (download5) {
			new WebSiteBean(5, "meitulu", "https://mtl.ttsqgs.com/images/img/%d/%d.jpg", "https://www.meitulu.com/item/3_2.html").downloadAll(new ImageProxy() {
				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					return String.format("%06d", albumIndex);
				}

				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}
			});
		}
		// not yet : java.io.FileNotFoundException: http://img.semici.com/album/16293/00011/001.jpg
		boolean download6 = false;
		if (download6) {
			new WebSiteBean(6, "semici", "http://img.semici.com/album/16293/%05d/%03d.jpg", "").downloadAll(new ImageProxy() {
				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					return String.format("%06d", albumIndex);
				}

				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}
			});
		}
		//
		boolean download7 = false;
		if (download7) {
			new WebSiteBean(7, "girlsky", "http://www.girlsky.cn/article/%d/%d/", "").downloadAll(new ImageProxy() {
				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					Element pictureElement = doc.getElementsByAttributeValue("id", "picture").get(0);
					Element imageShowElement = pictureElement.getElementsByTag("img").get(0);
					String imageUrl = imageShowElement.attr("src");
					return imageUrl;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					String albumName = doc.getElementsByAttributeValue("name", "description").get(0).attr("content");
					return albumName;
				}
			});
		}
		// not yet : this site is different with the others
		boolean download8 = false;
		if (download8) {
			new WebSiteBean(8, "zhyuge", "http://zhyuge.com/picture/detail?pictureId=%d", "").downloadAll(new ImageProxy() {
				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return null;
				}

				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					return null;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					return null;
				}
			});
		}
		//
		boolean download9 = false;
		if (download9) {
			new WebSiteBean(9, "mzitu", "http://www.mzitu.com/%d/%d", "http://www.mzitu.com/100/2").downloadAll(new ImageProxy() {
				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					Element mainImageElement = doc.getElementsByClass("main-image").get(0);
					Element imageShowElement = mainImageElement.getElementsByTag("img").get(0);
					String imageUrl = imageShowElement.attr("src");
					return imageUrl;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					Element mainImageElement = doc.getElementsByClass("main-image").get(0);
					Element imageShowElement = mainImageElement.getElementsByTag("img").get(0);
					String albumName = imageShowElement.attr("alt");
					if (albumName.lastIndexOf(" ") != -1) {
						albumName = albumName.substring(0, albumName.lastIndexOf(" "));
					}
					return albumName;
				}
			});
		}
		//
		boolean download10 = false;
		if (download10) {
			new WebSiteBean(10, "win4000", "http://www.win4000.com/meinv%d_%d.html", "").downloadAll(new ImageProxy() {
				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					String imageUrl = doc.getElementsByClass("pic-large").get(0).attr("url");
					return imageUrl;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					String albumName = doc.getElementsByAttributeValue("name", "keywords").get(0).attr("content");
					return albumName;
				}
			});
		}
		// not yet : can not find http://www.ili100.cn/a/17_1.html
		boolean download11 = false;
		if (download11) {
			new WebSiteBean(11, "ili100", "http://www.ili100.cn/a/%d_%d.html", "").downloadAll(new ImageProxy() {
				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					String imageUrl = "http://www.ili100.cn/" + doc.getElementById("bigimg").attr("src");
					return imageUrl;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					String albumName = doc.getElementById("T-bt").ownText();
					if (albumName.indexOf("(") > 0) {
						albumName = albumName.substring(0, albumName.indexOf("("));
					}
					return albumName;
				}
			});
		}
		// not yet : the same with download8
		boolean download12 = false;
		if (download12) {
			new WebSiteBean(12, "xiugirls", "http://www.xiugirls.com/album/%d", "").downloadAll(new ImageProxy() {
				@Override
				public String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex) {
					return String.format(pageUrlFormat, albumIndex, imageIndex);
				}

				@Override
				public String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex) {
					String imageUrl = "http://www.ili100.cn/" + doc.getElementById("bigimg").attr("src");
					return imageUrl;
				}

				@Override
				public String getAlbumName(Document doc, int albumIndex) {
					String albumName = doc.getElementById("T-bt").ownText();
					if (albumName.indexOf("(") > 0) {
						albumName = albumName.substring(0, albumName.indexOf("("));
					}
					return albumName;
				}
			});
		}
	}

	public String getPageUrlFormat() {
		return pageUrlFormat;
	}

	public WebSiteBean(int webIndex, String webName, String pageUrlFormat, String refererUrl) {
		this.webIndex = webIndex;
		this.webName = webName;
		this.pageUrlFormat = pageUrlFormat;
		this.refererUrl = refererUrl;
		downloadPath = DOWNLOAD_PATH + webName + "/";
	}

	public void downloadAll(ImageProxy imageProxy) {
		this.imageProxy = imageProxy;
		for (int i = 1; i < 20000; i++) {
			AlbumBean albumBean = new AlbumBean();
			albumBean.setWebIndex(webIndex);
			for (int k = 1; k < 200; k++) {
				String pageUrl = imageProxy.getPageUrl(pageUrlFormat, i, k);
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

	private ImageProxy imageProxy;

	private boolean downloadImageUrlFromPage(AlbumBean albumBean, String pageUrl, int albumIndex, int imageIndex) throws Exception {
		Document doc = null;
		/*
		 * need optimize ?????
		 */
		if (webIndex != 5 && webIndex != 6) {
			doc = Jsoup.connect(pageUrl).get();
		}
		String imageUrl = imageProxy.getImageUrl(doc, pageUrlFormat, albumIndex, imageIndex);
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

	/** download image from url to file */
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
		// con.setRequestProperty("Host", "www.meitulu.com");
		con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		con.setRequestProperty("Referer", refererUrl);
		con.setRequestProperty(
				"Cookie",
				"UM_distinctid=1654e424357610-091dd6d937f02e-3464790b-fa000-1654e424358148; CNZZDATA1255357127=443251054-1534616107-https%253A%252F%252Fwww.google.com.hk%252F%7C1534616107; Hm_lvt_1e2b00875d672f10b4eee3965366013f=1534616290; CNZZDATA1255487232=1541027707-1534610964-%7C1534616396");
		con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		imageProxy.setRequestHeader(con);
		con.setConnectTimeout(5 * 1000);
		con.connect();
		return con;
	}
}

abstract class ImageProxy {

	public abstract String getImageUrl(Document doc, String pageUrlFormat, int albumIndex, int imageIndex);

	public abstract String getAlbumName(Document doc, int albumIndex);

	public abstract String getPageUrl(String pageUrlFormat, int albumIndex, int imageIndex);

	public void setRequestHeader(HttpURLConnection urlConnection) {
	}
}
