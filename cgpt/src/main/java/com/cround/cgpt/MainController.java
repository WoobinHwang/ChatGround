package com.cround.cgpt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;
	
	@GetMapping("/help")
	public String helpPage(Model model) {
		// TODO Auto-generated method stub
		String value = this.mainService.returnValue();
		
		// key라는 이름으로 value라는 값을 model에 담아서 프론트로 전송 
		model.addAttribute("key", value);
		return "HelpPage";
	}
	
	@GetMapping("/")
	public String mainPage() {
		// TODO Auto-generated method stub
		return "MainPage";
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		// TODO Auto-generated method stub
		return "SignupPage";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		// TODO Auto-generated method stub
		return "LoginPage";
	}
	
	
}
