package com.zf.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zf.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);


}
