package com.example.service.freeboard;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Freeboard;
import com.example.repository.FreeboardRepository;

@Service
public class freeboardInfoService {
	
	@Autowired
	private HttpSession session; 
	
	@Autowired
	private FreeboardRepository freeboardRepository;
	
	public String getFreeboardPost(String StringFreeId) { 
		Long freeId = Long.parseLong(StringFreeId);
		Freeboard freeboard = freeboardRepository.findByFreeId(freeId);
		if(freeboard == null) { 
			return "freeboard";
		}
		 
		session.setAttribute("freeboard", freeboard);
		return "freeBoardInfo";
	}
	


}
