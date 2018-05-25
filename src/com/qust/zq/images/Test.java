package com.qust.zq.images;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String imageUrl = "http://www.baidu.com/a.jpg";
		String subString = imageUrl.substring(imageUrl.lastIndexOf("."));
		System.out.println(subString);
		System.out.println("hello world");
	}
}
