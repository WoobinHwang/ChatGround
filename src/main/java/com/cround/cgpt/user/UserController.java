package com.cround.cgpt.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService; 
	
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
	
	
}
