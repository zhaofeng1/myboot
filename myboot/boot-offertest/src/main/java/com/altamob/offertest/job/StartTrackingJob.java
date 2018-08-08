package com.altamob.offertest.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class StartTrackingJob {

	@Scheduled(cron = "0 */1 * * * ? ")
	public void startTracking() {
		System.out.println("job start");
	}
}
