package com.altamob.offertest.control;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.altamob.offertest.model.vo.ReqData;
import com.altamob.offertest.service.FeedOfferService;
import com.altamob.offertest.tracking.OfferTracking;
import com.altamob.offertest.util.DateUtils;
import com.altamob.offertest.util.OfferTrackingLog;

@RestController
@RequestMapping(value = "/admin")
public class AdminControl {

	private static Logger log = Logger.getLogger(AdminControl.class);

	@Autowired
	FeedOfferService feedOfferService;

	@RequestMapping("/tracking")
	public String tracking(String path, int num) {
		log.info("index start!");
		log.info("pahth:" + path);
		if (StringUtils.isNotBlank(path)) {
			OfferTracking.startTracking(path, num);
		} else {
			log.info("path is error:" + path);
		}
		return "ok";

	}

	@PostMapping("/callback")
	public String callback(@RequestBody JSONObject reqBody) {
		if (reqBody != null && !reqBody.isEmpty()) {
			try {
				log.info("reqBody:" + reqBody.toJSONString());
				String id = reqBody.getString("id");
				ReqData reqData = OfferTracking.getReqFromAsyncResultMap(id);
				String offerid = "";
				String country = "";
				String start = "";
				String clickurl = "";
				String end = DateUtils.dateToString(new Date(), null);
				if (reqData != null) {
					OfferTracking.asyncResultMap.remove(id);
					offerid = reqData.getOfferid();
					country = reqData.getGeo();
					start = reqData.getStart();
					clickurl = reqData.getClickurl();

					String logTxt = OfferTracking.getLogTemp(reqBody, offerid, country, clickurl, "10", start, end);
					OfferTrackingLog.logStr(logTxt);
				} else {
					log.error("not find reqData:" + id);
				}

			} catch (Exception e) {
				log.error("AdminControl.callback:", e);
			}
		} else {
			log.info("reqBody:" + reqBody.toJSONString());
		}
		return "ok";
	}

	@RequestMapping("/asyncTracking")
	public String asyncTracking(int offernum, int threadnum) {
		List<Object[]> list = feedOfferService.getLastHourOffer();
		List<Object[]> tempList = null;
		if (list != null) {
			if (list.size() > offernum) {
				tempList = list.subList(0, offernum);
			} else {
				tempList = list;
			}
			OfferTracking.startTrackingFromDb(tempList, threadnum);
		}
		return "ok";

	}

	@RequestMapping("/getReqmap")
	public String getReqmap() {
		return JSON.toJSONString(OfferTracking.asyncResultMap);

	}
}
