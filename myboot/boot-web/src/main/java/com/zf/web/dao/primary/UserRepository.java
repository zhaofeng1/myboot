package com.zf.web.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zf.web.model.primary.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);


}
