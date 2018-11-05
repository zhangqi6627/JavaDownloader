package com.qust.zq.images;

import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImageDownloader3 {
	// http://www.youzi4.cc/mm/20648/20648_1.html
	// http://www.47843.com/
	public static final String DOWNLOAD_PATH = Constants.DOWNLOAD_PATH + "/youzi5/";
	public final static String PAGE_URL_FORMAT = "http://www.youzi4.cc/mm/%d/%d_%d.html";
	//
	public final static int WEB_INDEX_1 = 1;
	public final static int WEB_INDEX_2 = 2;
	public final static int WEB_INDEX_3 = 3;
	public final static int WEB_INDEX_4 = 4;
	public final static int WEB_INDEX_5 = 5;
	public static void main(String[] args) {
		int webIndex = 3;
		for (int i = 1; i < 20000; i++) {
			AlbumBean albumBean = new AlbumBean();
			for (int k = 1; k < 200; k++) {
				String pageUrl = String.format(PAGE_URL_FORMAT, i, i, k);
				switch(webIndex){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
					
				}
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
		}
	}
	private static boolean downloadImageUrlFromPage(String pageUrl, int folderIndex, int imageIndex) throws Exception {
		Document doc = Jsoup.connect(pageUrl).get();
		Element imageShowElement = doc.getElementsByClass("IMG_show").get(0);
		String imageUrl = imageShowElement.attr("src");
		System.out.println("downloading imageUrl:" + imageUrl);
		String imageFolderName = folderIndex + "_" + imageShowElement.attr("alt");
		File imageFolder = new File(DOWNLOAD_PATH + imageFolderName);
		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}
		String imageName = String.format(folderIndex + "_%03d", imageIndex);
		String subFix = imageUrl.substring(imageUrl.lastIndexOf("."));
		imageName += subFix;
		File imageFile = new File(DOWNLOAD_PATH + imageFolderName + "/" + imageName);
		if (imageFile.exists()) {
			return false;
		}
		ImageUtils.downloadImage(3, imageUrl, DOWNLOAD_PATH, imageFolderName, imageName);
		return true;
	}
}
