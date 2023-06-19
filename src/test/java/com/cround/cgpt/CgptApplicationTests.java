package com.cround.cgpt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cround.cgpt.user.UserList;
import com.cround.cgpt.user.UserRepository;

@SpringBootTest
class CgptApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
		System.out.println("test print");
	}
	
	@Test
	void findUser() {
		UserList user = this.userRepository.findByusername("qqq");
		System.out.println(user);
	}

}
