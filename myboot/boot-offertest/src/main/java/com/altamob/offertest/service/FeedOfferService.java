package com.altamob.offertest.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altamob.offertest.util.DateUtils;

@Component
public class FeedOfferService {

	private static Logger logger = Logger.getLogger(FeedOfferService.class);

	@Autowired
	private EntityManager entityManager;

	public List<Object[]> getLastHourOffer() {
		//		String sql = "select id,appid from feed_offers where id = 69773628";
		String start = DateUtils.getHour(-1);
		String end = DateUtils.getHour(0);
		List<Object[]> result = new ArrayList<Object[]>();
		
		//获取状态正常的source
		List<String> sourceList = this.getPublishSource();
		if(sourceList==null||sourceList.size()==0){
			logger.error("FeedOfferService.getPublishSource error sourcelist is nul or empty!");
		}

		String sql = "select fo.id,ft.code,fo.platform,fo.appid,fo.source from feed_offers fo,feed_offer_target ft where fo.id=ft.offer_id and fo.status in('active','published') and fo.offer_time between :start and :end";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);

		List<Object[]> queryList = (List<Object[]>) query.getResultList();
		for (Object[] obj : queryList) {
			if (sourceList.contains(obj[4] + "")) {
				result.add(obj);
			}
		}
		return result;
	}

	public List<String> getPublishSource() {
		String sql = "select source_code from ok_source_config where status='published'";

		Query query = entityManager.createNativeQuery(sql);

		return (List<String>) query.getResultList();
	}
}
