package com.qust.zq.commic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.qust.zq.images.Constants;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		/*
		System.setProperty("webdriver.chrome.driver", "/Users/zhangqi/Desktop/movie/chromedriver");
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("https://toomics.com/sc/webtoon/detail/code/96674/ep/1/toon/4760");
		// System.out.println(webDriver.getPageSource());
		WebElement button_yes = webDriver.findElement(By.className("button_yes"));
		// System.out.println("btn_yes:" + button_yes);
		button_yes.click();
		System.out.println("clicked:" + webDriver.getPageSource());
		List<WebElement> webElements = webDriver.findElements(By.tagName("img"));
		for (WebElement element : webElements) {
			System.out.println(element.getAttribute("data-original"));
		}
		*/
		/*
		 https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947295746.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947297465.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947298234.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947299135.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947299983.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947300905.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947301915.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947302968.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947303956.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947304688.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947305655.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947306648.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947307682.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947308683.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947309645.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947310638.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947311518.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947312424.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947313392.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947314444.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947315353.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994731644.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994731745.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947318412.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947319376.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947320277.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947321317.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947322207.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947323113.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947324154.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947325249.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947326278.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947327265.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947328241.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947329327.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947330422.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947331504.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947332526.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947333514.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994733435.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947335137.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947336131.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947336959.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947337879.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947338795.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947339666.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_153199473407.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947341618.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994734257.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947343437.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947344352.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947345264.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947346169.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947347155.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947348134.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947349026.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947349943.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947350899.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947351751.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994735264.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947353558.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947354466.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994735536.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947356256.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947357161.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947358032.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947358899.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994735979.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947360782.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947361758.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947362664.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947363555.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994736454.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947365514.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947366464.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947367372.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947368505.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947369388.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947370382.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994737127.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947372255.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947373299.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947374313.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947375345.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947376154.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947377135.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947378133.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994737901.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947379833.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947380653.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947381705.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947382724.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947383635.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947384629.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947385453.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947386408.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947387328.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947388227.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947389076.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947389893.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947390717.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947391553.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947392505.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994739341.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947394461.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947395302.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947396289.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947397207.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947398123.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947399035.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947400025.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947400939.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947401931.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947402853.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947403826.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_1531994740474.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947405679.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947406523.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947407383.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947408286.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947409192.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947410094.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947410987.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947411776.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947412666.jpg
https://toon-g.toomics.com/upload/contents/20180717123724/1/2018_07_19_15319947413523.jpg
		 */
		try {
			downloadImage("https://toon-g.toomics.com/upload/contents/20180717123724/2/2018_07_19_15319950385115.jpg", "commic", "aaa.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//https://toomics.com/sc/webtoon/detail/code/96700/ep/1/toon/4720&ul=en-us&de=UTF-8&dt=%E4%BD%A0%E5%92%8C%E6%88%91%E7%9A%84%E5%B0%8F%E7%A7%98%E5%AF%86%201%20-%20%E7%8E%A9%E6%BC%AB%20Toomics&sd=24-bit&sr=1280x800&vp=375x651&je=0&_u=QACAAEAB~&jid=831064107&gjid=1119794713&cid=792119970.1540988700&tid=UA-114646527-1&_gid=1566044187.1540988700&_r=1&gtm=2wgam0NBQDHTF&z=330523503
	}
	public static void downloadImage(String urlString, String folderName, String filename) throws Exception {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		// con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		// con.setRequestProperty("Accept-Encoding", "gzip, deflate");
		// con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7");
		// con.setRequestProperty("Cache-Control", "max-age=0");
		// con.setRequestProperty("Connection", "keep-alive");
		// con.setRequestProperty("Cookie", "BUSER=14926814c9082dbea329f1e363ca86df");
		con.setRequestProperty("Referer", "https://toomics.com/sc/webtoon/detail/code/96675/ep/2/toon/4760");
		// con.setRequestProperty("Host", "www.youzi4.cc");
		// con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
		con.setConnectTimeout(5 * 1000);
		con.connect();
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
	public static final String DOWNLOAD_PATH = Constants.DOWNLOAD_PATH + "/commic/";
}
