package com.zf.web.dao.secondary;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zf.web.model.secondary.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPrimaryRepositoryTest {

	@Autowired
	UserSecondaryRepository userSecondaryRepository;

	@Test
	public void findAllTest() {
		List<User> list = userSecondaryRepository.findAll();
		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void getByUsername() {
		User user = userSecondaryRepository.findByUsername("zf");
		System.out.println(JSON.toJSONString(user));
	}

	@Test
	public void saveTest() {
		User user = new User();
		user.setUsername("cs");
		user.setUserpwd("123");

		userSecondaryRepository.save(user);
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	public void saveRollbackTest() throws Exception {
		User user = new User();
		user.setUsername("cs1");
		user.setUserpwd("123");

		userSecondaryRepository.save(user);
		//		throw new Exception("sysconfig error");
	}
}
