package com.altamob.offertest.tracking;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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
		String s = "";
		while (true) {
			try {
				if (OfferTracking.offerGeoQueue.isEmpty()) {
					break;
				}
				s = OfferTracking.offerGeoQueue.poll();

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
						String responseStr = OfferTracking.getResultFromOfferTestAsync(country, platform, realClickUrl);
						if (StringUtils.isNotBlank(responseStr)) {
							//
							ReqData reqData = new ReqData();
							reqData.setClickurl(realClickUrl);
							reqData.setOfferid(offerid);
							reqData.setGeo(country);
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
