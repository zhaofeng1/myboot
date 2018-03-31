package com.zf.web.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zf.web.dao.UserRepository;

@Controller
@RequestMapping(value = "/page")
public class PageControl {

	private static Logger log = Logger.getLogger(PageControl.class);

	@Autowired
	UserRepository userRepository;

	//	@RequestMapping(value = "/index")
	//	@RequestMapping("/index")

	//	@RequestMapping(value = "/index", method = RequestMethod.GET)
	//	public ModelAndView toIndex() {
	//		log.info("test start!");
	//		return new ModelAndView("test");
	//	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String toTest() {
		log.info("test start!");
		return "test";
	}


	@RequestMapping(value = "/firstThymeleaf", method = RequestMethod.GET)
	public String tofirstThymeleaf(Model model) {
		log.info("firstThymeleaf start!");
		model.addAttribute("hello", "PageControl.tofirstThymeleaf!");
		return "firstThymeleaf";
	}



}
