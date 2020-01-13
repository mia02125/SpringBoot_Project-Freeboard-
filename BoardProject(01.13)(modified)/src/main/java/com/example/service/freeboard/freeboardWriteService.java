package com.example.service.freeboard;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Freeboard;
import com.example.repository.FreeboardRepository;

@Service
public class freeboardWriteService {
	
	@Autowired
	private FreeboardRepository freeboardRepository;
	
	public void write(String title, String content, String writer, String insertDate, String updateDate) { 
		Freeboard freeboard = new Freeboard();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDate = LocalDateTime.now().format(dateFormat);
		freeboard.setTitle(title);
		freeboard.setContent(content);
		freeboard.setWriter(writer);
		freeboard.setInsertDate(currentDate);
		freeboardRepository.save(freeboard);
	}
}
