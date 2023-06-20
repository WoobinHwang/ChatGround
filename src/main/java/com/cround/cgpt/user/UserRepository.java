package com.cround.cgpt.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserList, String>{
	
	UserList findByusername(String username);
	
}
