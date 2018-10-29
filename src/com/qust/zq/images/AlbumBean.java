package com.qust.zq.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class AlbumBean {
	private int webIndex;
	private int pageIndex;
	private String title;
	private String albumImage;
	private int albumSize;
	private ArrayList<String> images;

	public AlbumBean() {

	}

	public AlbumBean(JSONObject jsonObject) {
		int webIndex = jsonObject.getInt("WebIndex");
		int pageIndex = jsonObject.getInt("PageIndex");
		String title = jsonObject.getString("Title");
		String albumImage = jsonObject.getString("AlbumImage");
		int albumSize = jsonObject.getInt("AlbumSize");
		System.out.println("webIndex:" + webIndex + " pageIndex:" + pageIndex + " title:" + title + " albumImage:" + albumImage + " albumSize:" + albumSize);
		JSONArray jsonArray = jsonObject.getJSONArray("Images");
		for (Object imageUrl : jsonArray) {
			System.out.println(imageUrl);
		}
	}

	public AlbumBean setWebIndex(int webIndex) {
		if (this.webIndex != webIndex) {
			this.webIndex = webIndex;
		}
		return this;
	}

	public AlbumBean setPageIndex(int pageIndex) {
		if (this.pageIndex != pageIndex) {
			this.pageIndex = pageIndex;
		}

		return this;
	}

	public AlbumBean setTitle(String title) {
		if (this.title == null) {
			this.title = title;
		}

		return this;
	}

	public AlbumBean setAlbumImage(String albumImage) {
		if (this.albumImage == null) {
			this.albumImage = albumImage;
		}
		return this;
	}

	public AlbumBean addImage(String image) {
		if (images == null) {
			images = new ArrayList<>();
		}
		images.add(image);
		albumSize = images.size();
		return this;
	}

	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("WebIndex", webIndex);
		jsonObject.put("PageIndex", pageIndex);
		jsonObject.put("Title", title);
		jsonObject.put("AlbumImage", albumImage);
		jsonObject.put("AlbumSize", albumSize);
		JSONArray jsonArray = new JSONArray(images);
		jsonObject.put("Images", jsonArray);
		return jsonObject;
	}

	public static void main(String[] args) {
		AlbumBean mAlbumBean = new AlbumBean().setWebIndex(4).setPageIndex(1).setTitle("meitu11").setAlbumImage("www.baidu.com").addImage("www.gg.com").addImage("www.yy.com");
		System.out.println("a:" + mAlbumBean.toJson());
		AlbumBean albumBean = new AlbumBean(mAlbumBean.toJson());
	}
}
