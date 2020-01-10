package com.example.service;

import javax.servlet.http.HttpServletRequest;

import com.example.model.Users;
import com.example.repository.UsersRepository;



public class JoinService {
	public void joinUser(HttpServletRequest request, UsersRepository usersRepository) { 
		
		String userId = request.getParameter("user_id");
		String userPw = request.getParameter("user_pw");
		String userName = request.getParameter("user_name");
		
		Users user = new Users();
		user.setUser_id(userId);
		user.setUser_pw(userPw);
		user.setUser_name(userName);
		
		usersRepository.save(user);
		
		
		
		
		
	}
}
