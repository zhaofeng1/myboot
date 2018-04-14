package com.zf.web.control;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zf.web.dao.primary.UserRepository;
import com.zf.web.model.primary.User;

@RestController
@RequestMapping(value = "/common")
public class CommonControl {

	private static Logger log = Logger.getLogger(CommonControl.class);

	@Autowired
	UserRepository userRepository;

	//	@RequestMapping(value = "/index")
	@RequestMapping("/index")
	public String home() {
		log.info("index start!");
		List<User> list = userRepository.findAll();
		System.out.println(JSON.toJSONString(list));
		return "Hello World! index";
		
	}

	//	@RequestMapping("/index")
	//	public String home() {
	//		log.info("index start!");
	//		return "Hello World! index";
	//		
	//	}

}
