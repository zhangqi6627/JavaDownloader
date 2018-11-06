package com.qust.zq.images;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * java 如何访问https请求
 */
public class Main {
	// https://www.ituba.cc/siwa/53171.html
	// https://www.uumnt.cc/xinggan/28599.html
	// http://www.umei.cc/meinvtupian/meinvxiezhen/130851_1.htm
	// http://www.jj20.com/bz/nxxz/
	// ///////////////////////////////////////////////////////////////////////////// http://www.mzitu.com/
	// http://www.duitoo.com/ent/lianglimeimo/83922.html
	// https://www.192tt.com/meitu/38733.html
	// http://www.mmonly.cc/mmtp/xgmn/235873_29.html
	// http://www.gtmm.net/swmn/1234.html
	// https://www.rtz.cc/html/siwameitui/
	// http://pic.720weixin.com/18jin/1171.html
	// http://www.27270.com/ent/meinvtupian/2018/273580.html 超美
	// ///////////////////////////////////////////////////////////////////////////// http://www.17786.com/3419_1.html
	// http://www.msntk.com/siwa/742.html
	// ///////////////////////////////////////////////////////////////////////////// http://www.mm4000.com/meinv/1.html 19610
	// ///////////////////////////////////////////////////////////////////////////// http://www.xiugirls.com/album/23860
	// http://www.360meimei.com/siwa/
	// http://www.meituba.com/xinggan/63977.html
	// http://www.gn899.com/41226 ------------------server error
	// http://tu.jiachong.net/a/siwameinv/198467.html
	// http://m.136ysw.com/a/jiankangtuku/xingganmeinv/38.html
	// https://www.nvshens.com/g/27668/
	// http://www.5857.com/meixiong/
	// http://www.mmxze.com/sexmv/748.html#2
	// http://www.mm035.com/gaoqing/20171219223032.html
	// http://www.nvshenge.com/mntp/1000.html
	// http://www.immtp.com/siwameinv/69.html
	// http://www.94xmn.com/xingganmeinv/2018/1017/1312.html
	// http://www.yuanfentk.com/tuku/mingxing/4466.html
	// http://www.260111.com/siwameitui/201806/35245.html
	// https://rtz.nhxdfs.com/html/siwameitui/cwph50421.htm
	// http://www.meinv4493.com/xinggan/7590.html
	// https://www.jpxgyw.com/Xiuren/Xiuren9789.html
	// http://www.2mm.cc/siwa/2017/0425/1871.html
	// http://www.kutuhui.com/meitu/21520.html
	// http://www.meinv01.com/meinv/88/2911.html
	// http://www.581148.com/10830.html
	// http://www.ppmsg.org/xingganmeinv/201810/23948.html
	// http://www.sangyy.cn/mingxingmeinv/20.html
	// http://bbs.beicei.com/forum.php
	// http://www.setuw.com/
	// http://www.the6688.com/sexmv/ningmengvivixinggantupian.html
	// http://www.ghost64.com/qqtouxiang/gexing/82163.html
	// https://www.uumtu.com/xinggan/29072.html
	// https://www.169tp.com/gaogensiwa/
	// http://www.duomeitu.com/mntp/mnmt/7230.html
	// http://www.mm4000.com/meinv/20441.html
	// https://www.qqretu.com/zhifusiwa/34039.html
	// http://www.mtyoyo.com/xingganmn/11760.html
	// http://www.5amn.com/meinv/93/3045.html
	// https://www.u313.cn/qingchun/75.html
	// http://www.tupianzj.com/meinv/20150729/22212.html
	// http://mm.allcoolmen.com/jk/201709/157.html
	// https://www.u313.cn/xinggan/14985_2.html
	// http://www.qq325.com/korea/20120202235_2.html
	// http://www.bpydw.com/qingchun/75.html 慢
	// https://www.rtz.cc/html/siwameitui/bgeh21743.htm
	// http://www.92mmdy.com/index.php?Article-index-id-96.html
	// http://tu.aiai6.com/meinv/2016/0108/185565_4.html
	// https://www.24mn.org/2018/10-29/tuimo33663_2.html
	// https://www.99c9.com/xinggan/2330_6.html
	// http://www.zhiqi88.com/tephymzbjnxzjxhscnwoltwpy/
	// http://www.liangtupian.com/meinv/
	// http://www.131mmw.com/html/xingganmeinv/20181106/4843.html
	// http://www.2345meinv.com/siwa/2444.html
	// http://www.vesm.net/xg/54174.html
	// http://www.ccmntu.com/qcmn/1004.html
	// http://meinv.ixiues.com/weimei/3209.html
	// http://www.mm035.com/gaoqing/20181030234207.html
	// http://www.5151cai.org/xinggan/8226.html
	// http://www.ilovgou.com/xg/54015_2.html
	// http://www.yesemn.com/mm/xingganmeinv/2014/0306/680_2.html
	// http://www.meinv68.com/hanguo/528_2.html
	// http://www.meinvha.com/qingchun/1974_2.html
	// https://www.meituri.com/a/3000/
	// http://5199168.com/1.html
	// http://www.mmjpeg.com/meitu/145.html
	// http://www.mxgjd.com/xinggan/9043-2.html
	// http://www.21900.net/meinv/527_2.html
	// http://www.99mm.me/xinggan/2997.html
	// http://www.lukayi.com/gaoqing/20180516001633-18.html
	// http://www.mm8mm88.com/tupian/1-2.html
	// http://www.mtuol.com/meinv/nvyou/3937_2.html
	// http://www.guotuw.com/xiezhen/9585.html
	// http://www.jitaotu.com/qingchun/70226_2.html
	// https://www.sxtp.net/meinv/201802/45243.html
	// http://www.chuntiancat.com/qingchun/1530_2.html
	// http://www.nvshentu.com/xingganmeinv/10185.html
	// https://www.27mn.com/meinv/851.html
	// ///////////////////////////////////////////////
	// ======================sesesesese=======================
	// https://www.xnxx.com/video-j57df60/_18av.cc
	// http://www.18cang.com/
	// http://596qq.com/
	// http://745ss.com/
	// http://981dd.com/
	// http://816ee.com
	// http://www.cnfeedraw.com/
	// http://www.38ts.com/
	// http://www.cqsyhr.com
	// nbxts.com
	// http://www.cjx111.com/html/article/index1.html
	// http://woai22.com/
	// http://www.alldiangroup.com/html/article/index2000.html
	// http://www.ahhncl.net/html/article/index4372.html
	// http://www.11zl.com/
	// http://www.dxj955.com/?m=art-type-id-15.html
	// http://www.gxiao8.com/p02/index.html
	// http://www.em65.com/html/article/index1759.html
	public static void main(String[] args) {
		// load17786();
		// loadKFJ();
		load131();
	}
	// 很卡 http://www.mm131.com/xinggan/1501.html ---- http://www.mm131.com/xinggan/4265.html
	private static void load131() {
		String[] tags = new String[] { "qingchun", "xinggan", "xiaohua", "chemo", "qipao", "mingxing" };
		String urlFormat = "http://www.mm131.com/%s/%d.html";
		for (int i = 1; i < 100; i++) {
			// System.out.println("" + i);
			for (String tag : tags) {
				String urlString = String.format(urlFormat, tag, i);
				try {
					Jsoup.connect(urlString).get();
					System.out.println("suc:" + urlString);
					break;
				} catch (IOException e) {
					// e.printStackTrace();
					// System.out.println("fai:" + urlString);
				}
			}
		}
	}
	// https://kongfj.com/?p=3145
	private static void loadKFJ() {
		for (int i = 1; i < 4000; i++) {
			String urlString = "https://kongfj.com/?p=";
			try {
				Jsoup.connect(urlString + i).timeout(5000).get();
				System.out.println(i + ":" + (urlString + i));
			} catch (IOException e) {
				System.out.println(i + ":" + e.getMessage());
			}
		}
	}
	// https://www.aitaotu.com/guonei/1.html
	private static void loadAiTaoTu() {
		String[] types = new String[] { "guonei", "rihan", "gangtai", "jingpin", "pinpai" };
		for (int i = 1; i < 1000; i++) {
			for (int k = 0; k < types.length; k++) {
				String urlString = "https://www.aitaotu.com/" + types[k] + "/" + i + ".html";
				try {
					Jsoup.connect(urlString).timeout(5000).get();
					System.out.println(urlString);
					break;
				} catch (IOException e) {
					// e.printStackTrace();
					if (k == types.length - 1) {
						System.out.println(i + " not found");
					}
				}
			}
		}
	}
	// http://92mntu.com/xgmn/1026.html
	private static void load92MNTU() {
		final String[] types = new String[] { "qcmn", "xgmn", "swmt", "rhmn", "mncm", "mnmx" };
		// 92mntu
		final int count = 1;
		for (int i = 1; i <= count; i++) {
			final int n = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					long beginTime = System.currentTimeMillis();
					for (int k = 0; k < 20000; k++) {
						int m = k * count + n;
						for (int t = 0; t < types.length; t++) {
							try {
								// String urlString = "http://www.mm131.com/qingchun/" + m + ".html";
								String urlString = "http://92mntu.com/" + types[t] + "/" + m + ".html";
								Jsoup.connect(urlString).timeout(5000).get();
								System.out.println(urlString);
								break;
							} catch (IOException e) {
								// e.printStackTrace();
								// System.out.println(m + " fail");
							}
						}
					}
					long endTime = System.currentTimeMillis();
					System.out.println("spends " + (endTime - beginTime));
				}
			}).start();
		}
		//
	}
}
