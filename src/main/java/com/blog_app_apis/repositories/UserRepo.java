 package com.blog_app_apis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_app_apis.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findById(Integer userId);

	User findByEmail(String email);
	

	

}
