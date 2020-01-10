package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UsersRepository;
@Service
public class joinService {
	
	@Autowired
	private UsersRepository usersRepository; //유저값 저장하는 곳
	
	@Autowired
	private UserPasswordHashClass userPasswordHashClass; 
	
	public String joinUser(String userId, String userPw, String userName) { 
		if(userId.equals("") || userPw.equals("") || userName.equals("")) { 
			return "join";
		} else { 
			Users users = new Users();
			users.setUserid(userId);
			String hashPw = userPasswordHashClass.getSHA256(userPw);
			//암호화
			users.setPassword(hashPw);
			users.setUsername(userName);
			usersRepository.save(users);
			return "index";
		}
	}
}
