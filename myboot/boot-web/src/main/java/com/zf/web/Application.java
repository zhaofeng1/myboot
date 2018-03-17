package com.zf.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// @RestController
// @EnableAutoConfiguration
// @ComponentScan(basePackages = { "com.zf.web.*" })
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	//	@RequestMapping("/")
	//	String home() {
	//		return "Hello World!111111112";
	//	}

}
