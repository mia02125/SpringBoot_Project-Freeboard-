package com.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import com.example.model.Users;
import com.example.repository.UsersRepository;

@Service
public class imageService {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	
	public String imageInsert(@PathVariable("id") Long id, MultipartFile imageFile) { 
		try {
			Users users = new Users();
			DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String currentDate = LocalDateTime.now().format(dateTimeFormat);
			Users image = usersRepository.findByUseridAndPassword(users.getUserid(), users.getPassword());
			image.setName(imageFile.getOriginalFilename());
			image.setData(imageFile.getBytes());
			image.setRegDate(currentDate);
			usersRepository.save(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myInfo";
	}
}
