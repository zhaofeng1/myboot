package com.zf.web.control;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/common")
public class CommonControl {

	private static Logger log = Logger.getLogger(CommonControl.class);

	//	@RequestMapping(value = "/index")
	@RequestMapping("/index")
	public String home() {
		log.info("index start!");
		return "Hello World! index";
	}
}
