package com.example.service.freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Freeboard;
import com.example.repository.FreeboardRepository;

@Service
public class freeboardWriteService {
	
	@Autowired
	private FreeboardRepository freeboardRepository;
	
	public void write(String title, String content, String writer) { 
		Freeboard freeboard = new Freeboard();
		freeboard.setTitle(title);
		freeboard.setContent(content);
		freeboard.setWriter(writer);
		freeboardRepository.save(freeboard);
	}
}
