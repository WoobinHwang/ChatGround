package com.cround.cgpt.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserList signup(String username, String password
			, String nickname, String email) {
		// TODO Auto-generated method stub
		UserList user = new UserList();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setNickname(nickname);
		user.setEmail(email);
		
		this.userRepository.save(user);
		
		return user;
	}
	
	
}
