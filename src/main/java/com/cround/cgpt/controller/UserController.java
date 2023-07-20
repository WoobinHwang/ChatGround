package com.cround.cgpt.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cround.cgpt.dao.UserRepository;
import com.cround.cgpt.entity.UserList;
import com.cround.cgpt.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserServiceImpl userService; 
	
	@GetMapping("/signup")
	public String signupPage() {
		// TODO Auto-generated method stub
		return "SignupPage";
	}
	
	@PostMapping("/signup")
	public String signup(UserList user) {
		
		System.out.println("컨트롤러 signup 시작");
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("nickname : " + user.getNickname());
		System.out.println("email : " + user.getEmail());
		if(userService.signup(user.getUsername(), user.getPassword()
				, user.getNickname(), user.getEmail()) == null) {
			return "redirect:/user/signup";
		} ;
		System.out.println("컨트롤러 signup 끝");
		return "redirect:/user/login";
	}
	
	
	/** 중복된다면 true, 중복되지 않는다면 false를 반환 */
	@PostMapping(value = "/duplicate/check")
	@ResponseBody
	public boolean duplicateCheck(String column, String data) {
		
		 System.out.println("column : " + column);
		 System.out.println("data : " + data);
		
		try {
			return userService.duplicateCheck(column, data);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("알수없는 이유로 체크가 불가능하여 중복된것으로 반환");
			return true;
		}
		 
//		 return userService.duplicateCheck(column, data);
	}
	
	
	@GetMapping("/login")
	public String loginPage() {
		// TODO Auto-generated method stub
		return "LoginPage";
	}
	
	@PostMapping ("/edit")
	public String editInfoPage() {
		// TODO Auto-generated method stub
		return "EditInfomationPage";
	}
	
	@GetMapping("/logout")
	public String logout() {
		// TODO Auto-generated method stub
		return "redirect:/";
	}
	
	@GetMapping("/find/account")
	public String findAccountPage() {
		System.out.println("계정 찾기 페이지입니다.");
		return "FindAccountPage";
	}
	
	@GetMapping("/username") 
    @ResponseBody 
    public UserList currentUserName(Principal Principal) { 
		System.out.println("유저네임 찾는중");
		System.out.println(Principal);
		UserList userInfo = new UserList();
		
		try {
			String userID = Principal.getName();
			userInfo = userService.getUserInfo(userID);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return userInfo;
    } 
	
}
