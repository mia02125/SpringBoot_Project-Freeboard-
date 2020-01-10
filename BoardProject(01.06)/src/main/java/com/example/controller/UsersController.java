package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.repository.UsersRepository;
import com.example.service.JoinService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@PostMapping(value = "/joinRequest")
	public String joinRequest(HttpServletRequest request) { 
		// Index.html에서 요청을 보내면 컨트롤러해서 MainController쪽으로 이동해서 return "join"을 통해 join.html를 보여줌
		// join.html에서 가입하면 UsersController로 와서 처리
		JoinService joinService = new JoinService(); 
		joinService.joinUser(request, usersRepository); 
		return "index";
		
	}
	@PostMapping(value = "/loginRequest")
	public String loginRequest() { 
		return "index";
	}
}
