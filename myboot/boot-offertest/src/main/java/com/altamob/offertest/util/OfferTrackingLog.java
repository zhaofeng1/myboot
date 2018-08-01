package com.altamob.offertest.util;

import org.apache.log4j.Logger;


/**
 * 
 * @author zhaofeng
 *
 */
public class OfferTrackingLog {

	private static final Logger log = Logger.getLogger(OfferTrackingLog.class);
	
	public static void logStr(String msg) {
		log.info(msg);
	}
	
}
