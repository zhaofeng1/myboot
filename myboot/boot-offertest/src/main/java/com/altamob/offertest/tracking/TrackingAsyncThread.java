package com.altamob.offertest.tracking;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.altamob.offertest.model.vo.ReqData;
import com.altamob.offertest.util.DateUtils;

/**
 * 异步请求offertest
 * @author zhaofeng
 *
 */
public class TrackingAsyncThread implements Runnable {

	private static Logger logger = Logger.getLogger(OfferTracking.class);

	@Override
	public void run() {
		logger.info(Thread.currentThread().getId() + "start");
		String baseUrl = "http://ad.click.kaffnet.com/v1/tracking";
		String offerid = "174215926";
		String platform = "android";
		String country = "ID";
		String source = "10";
		String appid = "";
		ReqData reqData = null;
		while (true) {
			try {
				if (OfferTracking.offerGeoQueue.isEmpty()) {
					break;
				}
				reqData = OfferTracking.offerGeoQueue.poll();

				if (reqData != null) {
					logger.info("TrackingThread reqData:" + JSON.toJSONString(reqData));
					offerid = reqData.getOfferid();
					country = reqData.getGeo();
					platform = reqData.getPlatform();
					appid = reqData.getAppid();

					//根据offerid 生成连接
					String clickurl = OfferTracking.getClickByOferid(offerid, platform, country);
					//		System.out.println(baseUrl + clickurl);

					if (StringUtils.isNotBlank(clickurl)) {
						String realClickUrl = baseUrl + clickurl;
						String responseStr = OfferTracking.getResultFromOfferTestAsync(country, platform, realClickUrl, appid);
						if (StringUtils.isNotBlank(responseStr)) {
							//
							reqData.setClickurl(realClickUrl);
							reqData.setStart(DateUtils.dateToString(new Date(), null));
							OfferTracking.asyncResultMap.put(responseStr, reqData);
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
