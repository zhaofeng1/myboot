package com.zf.web.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.zf.web.dao.UserRepository;

public class ManagerService {

	@Autowired
	UserRepository userRepository;

	EntityManagerFactory entityManagerFactory;
	
	//使用这个标记来注入EntityManagerFactory 
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void test() {
		EntityManager em = entityManagerFactory.createEntityManager();
	}
}
