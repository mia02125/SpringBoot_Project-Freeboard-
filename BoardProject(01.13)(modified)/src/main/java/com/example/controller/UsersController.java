package com.example.controller;

import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.joinService;
import com.example.service.loginService;

@Controller
public class UsersController {
	
	//Service를 자동적으로 사용하게끔함!!!!!
	@Autowired 
	private joinService joinService; 
	
	@Autowired
	private loginService loginService;
	
//	@Autowired
//	private UsersRepository usersRepository; (보통 서비스에 넣어 사용하기 때문에 주석처리) 
 
	//회원가입 요청받은 값
	@PostMapping("/joinRequest")
	public String joinRequest(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("userid"); // join.html의 name값을 가져옴
		String userPw = paramMap.get("password"); // join.html의 name값을 가져옴
		String userName = paramMap.get("username");// join.html의 name값을 가져옴
		String page = joinService.joinUser(userId, userPw, userName);
		return page;
	}
	
	//로그인 요청받은 값
	@PostMapping("/loginRequest")
	public String loginRequest(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("userid"); //login.html의 name값을 가져옴
		String userPw = paramMap.get("password"); //login.html의 name값을 가져옴
		String page = loginService.login(userId, userPw);
		return page;	
	}

}
