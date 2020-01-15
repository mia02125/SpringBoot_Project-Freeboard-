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
		String currentDate = LocalDateTime.now().format(dateFormat); // String값인 현재 시간을 LocalDateTime값으로 변환
		freeboard.setTitle(title);
		freeboard.setContent(content);
		freeboard.setWriter(writer);
		freeboard.setInsertDate(currentDate);
		freeboardRepository.save(freeboard);
	}
	public void delete(Long freeId) {
		//delete하기 위한 컨트롤러를 위한 서비스 
		freeboardRepository.deleteById(freeId); 
		// freeboardRepository로부터 Id를 지우는 함수를 받는다.
		// freeboardController로 상속 
	}
	public void update(String title, String content, String updateDate) { 
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		String currentDate = LocalDateTime.now().format(dateFormat); // String값인 현재 시간을 LocalDateTime값으로 변환
//		freeboardRepository.save(title, content); <- 출근하자마자  update을 위한 Repository 다시만들기
//		                                          <- 방법2 : write() 비스무리하게 만들어보기 (아이디값은 바뀔 필요없고 title, content,updateDate값만 바뀌면 되는거니까..)
		
	}
}
