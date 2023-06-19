package com.cround.cgpt;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cround.cgpt.chat.ChatList;
import com.cround.cgpt.chat.ChatRepository;
import com.cround.cgpt.user.UserList;
import com.cround.cgpt.user.UserRepository;

@SpringBootTest
class CgptApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	
	@Test
	void contextLoads() {
		System.out.println("test...");
	}

	@Test
	void userInsert() {
		UserList user = new UserList();
		user.setUsername("아이디2");
		user.setPassword("비밀번호2");
		user.setNickname("닉네임2");
		user.setEmail("이메일2");
		this.userRepository.save(user);
	}
	
	@Test
	void chatInsert() {
		ChatList chat = new ChatList();
		// chat.setUsername("굄울쥐");
		chat.setContents("이잉 망나니");
		chat.setLocalDateTime(LocalDateTime.now());
		this.chatRepository.save(chat);
		
	}
	
	@Test
	void userSelect() {
		List<UserList> users = this.userRepository.findAll();
			for (UserList user : users) {
			System.out.println(user.toString());
			System.out.println(user.getUsername());
			System.out.println(user.getReportedCount());
		}
	}
	
	@Test
	void chatSelect() {
		List<ChatList> chats = this.chatRepository.findAll();
			for (ChatList chat : chats) {
			System.out.println(chat.getChat_num());
			// System.out.println(chat.getUsername());
			System.out.println(chat.getContents());
			System.out.println(chat.getLocalDateTime());
		}
	}
	
	
}
