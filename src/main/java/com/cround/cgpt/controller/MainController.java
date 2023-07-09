package com.cround.cgpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cround.cgpt.entity.UserList;
import com.cround.cgpt.service.MainService;

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
	
	@RequestMapping("/")
	public String mainPage(Model model , 
				  UserList user
			) {
		// TODO Auto-generated method stub
		System.out.println("메인 페이지");
		model.addAttribute(user);
//		System.out.println(principal.getName());
		System.out.println(model);
		
		
		return "MainPage";
	}
	
	@GetMapping("/admin")
	public String admin() {
		// TODO Auto-generated method stub
		return "AdminPage";
	}
	
}
