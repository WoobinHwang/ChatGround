package com.cround.cgpt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cround.cgpt.entity.UserList;

public interface UserRepository extends JpaRepository<UserList, String>{
	
	UserList findByusername(String username);
	
}
