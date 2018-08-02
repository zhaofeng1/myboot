package com.altamob.offertest.control;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altamob.offertest.tracking.OfferTracking;

@RestController
@RequestMapping(value = "/admin")
public class AdminControl {

	private static Logger log = Logger.getLogger(AdminControl.class);

	@RequestMapping("/tracking")
	public String tracking(String path) {
		log.info("index start!");
		log.info("pahth:" + path);
		if (StringUtils.isNotBlank(path)) {
			OfferTracking.startTracking(path);
		} else {
			log.info("path is error:" + path);
		}
		return "ok";

	}
}
