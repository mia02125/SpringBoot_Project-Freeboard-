package com.example.service.freeboard;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Freeboard;
import com.example.repository.FreeboardRepository;

@Service
public class freeboardInfoService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HttpSession session; 
	
	@Autowired
	private FreeboardRepository freeboardRepository;
	
	public String getFreeboardPost(String StringFreeId) {
		try {
			Long freeId = Long.parseLong(StringFreeId);
			Freeboard freeboard = freeboardRepository.findByFreeId(freeId); //특정 freeId를 찾음 
			if(freeboard == null) { 
				return "freeboard";
			}
			session.setAttribute("freeboard", freeboard);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("freeboardInfoSerive error");
		}
		return "freeBoard/freeBoardInfo";
	}
}
