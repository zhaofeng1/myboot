package com.altamob.offertest.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedOfferServiceTest {

	@Autowired
	FeedOfferService feedOfferService;//省略

	@Test
	public void getLastHourOfferTest() {
		feedOfferService.getLastHourOffer();
	}

	@Test
	public void getPublishSourceTest() {
		List<String> list = feedOfferService.getPublishSource();
		System.out.println(JSON.toJSONString(list));
	}
}
