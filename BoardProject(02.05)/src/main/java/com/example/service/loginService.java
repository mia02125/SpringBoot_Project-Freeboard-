package com.example.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UsersRepository;

@Service
public class loginService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserPasswordHashClass userPasswordHashClass;

	@Autowired
	private UsersRepository repository;

	@Autowired
	HttpSession session;

	//로그인에서도 hashCode를 받아와야하기 때문에 여기에도 선언 
	public String login(String userId, String userPw) {
		try {
			if(userId.equals("") || userPw.equals("")) { // userId나 userPW가 입력 안되었을 시  
				return "login"; // 로그인 
			} else {
				String hashPw = userPasswordHashClass.getSHA256(userPw);
				Users users = repository.findByUseridAndPassword(userId, hashPw);//repository.findByUseridAndPassword() : DB에서 정보를 요청 받아 처리(쿼리형태)
				if(users == null) {  //만약에 users값의 해시코드가 틀리다면 로그인페이지에 머무름
					return "login";
				}
				session.setAttribute("loginUser", users);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("LoginService Error");
		}
		return "index";
	}
}
