package com.altamob.offertest.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altamob.offertest.util.DateUtils;

@Component
public class FeedOfferService {

	@Autowired
	private EntityManager entityManager;

	public List<Object[]> getLastHourOffer() {
		//		String sql = "select id,appid from feed_offers where id = 69773628";
		String start = DateUtils.getHour(-1);
		String end = DateUtils.getHour(0);

		String sql = "select fo.id,ft.code,fo.platform,fo.appid from feed_offers fo,feed_offer_target ft where fo.id=ft.offer_id and fo.status in('active','published') and fo.offer_time between :start and :end";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);

		return (List<Object[]>) query.getResultList();
	}
}
