package com.altamob.offertest.tracking;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.altamob.offertest.util.OfferTrackingLog;

public class TrackingThread implements Runnable {

	private static Logger logger = Logger.getLogger(OfferTracking.class);

	@Override
	public void run() {
		logger.info(Thread.currentThread().getId() + "start");
		String baseUrl = "http://ad.click.kaffnet.com/v1/tracking";
		String offerid = "174215926";
		String platform = "android";
		String country = "ID";
		String source = "10";
		String s = "";
		while (true) {
			try {
				if (OfferTracking.offerGeoQueue.isEmpty()) {
					break;
				}
				//				s = OfferTracking.offerGeoQueue.poll();

				if (StringUtils.isNotBlank(s)) {
					logger.info("TrackingThread s:" + s);
					String[] sArray = s.split("\t");
					offerid = sArray[0];
					country = sArray[1];

					//根据offerid 生成连接
					String clickurl = OfferTracking.getClickByOferid(offerid, platform, country);
					//		System.out.println(baseUrl + clickurl);

					if (StringUtils.isNotBlank(clickurl)) {
						String realClickUrl = baseUrl + clickurl;
						String responseStr = OfferTracking.getResultFromOfferTest(country, platform, realClickUrl);
						if (StringUtils.isNotBlank(responseStr)) {
							try {
								JSONObject json = JSON.parseObject(responseStr);
								String logTxt = OfferTracking.getLog(json, offerid, country, realClickUrl, source);
								//							System.out.println(json.toJSONString());
								//							System.out.println(Thread.currentThread().getId() + ";" + logTxt);
								OfferTrackingLog.logStr(logTxt);
							} catch (Exception e) {
								logger.info("TrackingThread.run responseStr:" + responseStr);
								Thread.sleep(20 * 1000);
							}
						} else {
							logger.info("offerid:" + offerid);
						}
					}
				}
			} catch (Exception e) {
				logger.error("TrackingThread poll error:{0}", e);
			}

		}

	}

}
