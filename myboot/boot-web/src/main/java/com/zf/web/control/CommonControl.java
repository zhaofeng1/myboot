package com.zf.web.control;

import com.zf.web.dao.hasoffer.ProxyRepository;
import com.zf.web.service.hasoffer.ProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/common")
public class CommonControl {

	private static Logger log = LoggerFactory.getLogger(CommonControl.class);

	@Autowired
	ProxyService proxyService;

	@Autowired
	ProxyRepository proxyRatioRepository;

	@RequestMapping("/index")
	public String home() {
		log.info("index start!");
		return "Hello World! index";
		
	}

	@RequestMapping("/test")
	public String test() {
		long num = proxyRatioRepository.count();
		System.out.println("num:" + num);

		return "ok";
	}
}
