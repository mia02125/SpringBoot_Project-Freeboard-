package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@Autowired
	private HttpSession session;
	
	
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/joinPage")
	public String joinPage() {
		return "join";
	}
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login";
	}	
	@GetMapping("/logoutPage") 
	public String logout() {
		session.invalidate();
		return "index"; //UserController에 mapping된게 없어서 404오류 발생 
	}
	@GetMapping("/freeboardWritePage") 
	public String freeboardWritePage() {
		return "freeboardWritePage";   
	}
}
