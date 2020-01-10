package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UsersRepository;

@Service
public class loginService {
	
	@Autowired
	private UserPasswordHashClass userPasswordHashClass;
	
	@Autowired
	private UsersRepository repository;
	
	
	//로그인에서도 hashCode를 받아와야하기 때문에 여기에도 선언 
	public String login(String userId, String userPw) {
		if(userId.equals("") || userPw.equals("")) { 
			return "login";
		} else {
			String hashPw = userPasswordHashClass.getSHA256(userPw);
			Users users = repository.findByUseridAndPassword(userId, hashPw);
			//repository.findByUser_idAndUser_pw() : DB에서 정보를 요청 받아 처리(쿼리형태)
			if(users == null) {  //만약에 users값의 해시코드가 틀리다면 로그인페이지에 머무름
				return "login";
			}
			return "index";	
		}
	}
}
