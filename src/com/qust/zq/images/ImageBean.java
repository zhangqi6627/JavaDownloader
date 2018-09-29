package com.qust.zq.images;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class ImageBean {
	private String pageUrl;
	//http://www.mmjpg.com/mm/1
	public abstract String getImageTitle(String pageUrl);
}
