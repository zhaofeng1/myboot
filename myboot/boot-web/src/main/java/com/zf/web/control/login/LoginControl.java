package com.zf.web.control.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zf.web.control.PageControl;
import com.zf.web.dao.UserRepository;

@Controller
@RequestMapping(value = "/system")
public class LoginControl {

	private static Logger log = Logger.getLogger(PageControl.class);

	@Autowired
	UserRepository userRepository;


	@RequestMapping(value = "/tologin", method = RequestMethod.GET)
	public String toLogin() {
		log.info("tologin start!");
		return "login/login";
	}

}
