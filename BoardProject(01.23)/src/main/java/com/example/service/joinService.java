package com.example.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Users;
import com.example.repository.UsersRepository;


@Service
public class joinService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UsersRepository usersRepository; //유저값 저장하는 곳
	
	@Autowired
	private UserPasswordHashClass userPasswordHashClass; 
	
	public String joinUser(String userId, String userPw, String userName, String joinDate) { 
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
			String currentDate = LocalDateTime.now().format(dateFormat); // String값인 현재 시간을 LocalDateTime값으로 변환
			if(userId.equals("") || userPw.equals("") || userName.equals("")) { 
				return "join";
			} else { 
				Users users = new Users();
				users.setUserid(userId);
				String hashPw = userPasswordHashClass.getSHA256(userPw);
				//암호화
				users.setPassword(hashPw);
				users.setUsername(userName);
				users.setJoinDate(currentDate);
				usersRepository.save(users);
	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("JoinService Error");
			
		}
		return "index";
	}
	
}
