package com.altamob.offertest.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.altamob.offertest.service.FeedOfferService;
import com.altamob.offertest.tracking.OfferTracking;

@Component
@EnableScheduling
public class StartTrackingJob {

	private static Logger logger = Logger.getLogger(OfferTracking.class);

	private static int threadnum = 10;

	@Autowired
	FeedOfferService feedOfferService;

	@Scheduled(cron = "0 10 * * * ? ")
	public void startTracking() {
		logger.info("job start!");
		List<Object[]> list = feedOfferService.getLastHourOffer();
		if (list.size() > 0) {
			OfferTracking.startTrackingFromDb(list, threadnum);
		}
		logger.info("job end!");
	}
}
