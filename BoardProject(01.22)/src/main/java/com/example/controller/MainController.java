package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.repository.FreeboardRepository;

@Controller
public class MainController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private FreeboardRepository freeboardRepository;
	
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
		return "freeBoard/freeboardWritePage";   
	}
	@GetMapping("/mapPage")
	public String map() {
		return "map";
	}
	@GetMapping("/{freeId}/update")
	public String freeboardUpdatePage(ModelMap model, @PathVariable Long freeId) { 
	model.addAttribute("freeboardInfo", freeboardRepository.findByFreeId(freeId));
		return "freeBoard/freeboardUpdatePage";
	}
	@GetMapping("/MyInfo")
	public String MyInfo() { 
		return "MyInfo";
	}
}
