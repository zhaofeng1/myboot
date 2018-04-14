package com.zf.web.dao.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zf.web.model.secondary.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);


}
