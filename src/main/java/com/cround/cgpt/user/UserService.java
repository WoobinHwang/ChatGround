package com.cround.cgpt.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserList signup(String username, String password
			, String nickname, String email) {
		// TODO Auto-generated method stub
		
		UserList user = userRepository.findByusername(username); 
		if (user != null) {
			System.out.println("해당 아이디로 가입된 아이디가 이미 있습니다.");
			return null;
		}
		
		user = new UserList();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setNickname(nickname);
		user.setEmail(email);
		
		this.userRepository.save(user);
		
		return user;
	}

	// 로그인 프로세스를 진행 할 때 이 함수를 찾는 세팅이 자체적으로 있음
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserList user = userRepository.findByusername(username);
		
		if(user==null) {
			System.out.println("해당 아이디로 가입된 아이디가 없습니다.");
			throw new UsernameNotFoundException("해당 아이디는 없습니다.");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 아이디가 admin이면 관리자의 권한을 부여
		if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getAuthority()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getAuthority()));
        }
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
	
	
}
