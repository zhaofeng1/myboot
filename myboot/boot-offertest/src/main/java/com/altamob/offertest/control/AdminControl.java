package com.altamob.offertest.control;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.altamob.offertest.tracking.OfferTracking;

@RestController
@RequestMapping(value = "/admin")
public class AdminControl {

	private static Logger log = Logger.getLogger(AdminControl.class);

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
		log.info("callback start!");

		log.info("reqBody：" + reqBody.toJSONString());
		return "ok";
	}
}
