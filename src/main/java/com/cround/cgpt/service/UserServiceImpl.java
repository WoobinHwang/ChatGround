package com.cround.cgpt.service;

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

import com.cround.cgpt.UserRole;
import com.cround.cgpt.dao.UserRepository;
import com.cround.cgpt.entity.UserList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserList signup(String username, String password
			, String nickname, String email) {
		// TODO Auto-generated method stub
		username = username.toLowerCase();
		
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
		// 소문자로 변환
		username = username.toLowerCase();
		
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
	
	public boolean duplicateCheck(String column, String data) {
		// TODO Auto-generated method stub
		System.out.println("중복검사함수중...");
		// 넘어온 값을 소문자로 변환
		data = data.toLowerCase();
		UserList duplicatedData = null;
		
//		System.out.println("column : "+column);
//		System.out.println("column : "+column.getClass().getName());
		if (column.equals("username")) {
			duplicatedData = userRepository.findByusername(data);
//			System.out.println("중간 점검 duplicatedData :" + duplicatedData);
		} else if (column.equals("nickname")) {
			duplicatedData = userRepository.findBynickname(data);
		} else if (column.equals("email")) {
			duplicatedData = userRepository.findByemail(data);
		} else {
			System.out.println("컬럼을 확인할 수 없음");
		}
		
//		try {
//			System.out.println("최종 점검 duplicatedData :" + duplicatedData);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("duplicatedData는 null인것으로 추정");
//		}
		
		
		// 중복된다면 true
		// 중복되는게 없다면 false
		if (duplicatedData != null) {
			System.out.println("중복되는게 있습니다.");
			return true;
		} else {
			System.out.println("중복되는게 없습니다.");
			return false;
		}	
	}

	public UserList getUserInfo(String username) {
		// TODO Auto-generated method stub
		UserList accountInfo = userRepository.findByusername(username);
		UserList userInfo = new UserList();
		userInfo.setUsername(accountInfo.getUsername());
		userInfo.setNickname(accountInfo.getNickname());
		userInfo.setEmail(accountInfo.getEmail());
		
		return userInfo;
	}
	
}
