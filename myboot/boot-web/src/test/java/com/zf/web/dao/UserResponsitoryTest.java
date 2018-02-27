package com.zf.web.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.zf.web.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResponsitoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void test() {
		List<User> list = userRepository.findAll();
		System.out.println(JSON.toJSONString(list));
	}
}
