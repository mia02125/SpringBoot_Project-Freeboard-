package com.example.service.freeboard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.model.Freeboard;
import com.example.pageMaker.PageMaker;
import com.example.repository.FreeboardRepository;

@Service
public class freeboardService {

	@Autowired
	private FreeboardRepository freeboardRepository;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private PageMakerService pageMakerService;
	public String list(int pageNum) {
		
		PageMaker pagemaker = pageMakerService.generatePageMaker(pageNum, 10, freeboardRepository); 
		   
		PageRequest pageRequest = PageRequest.of(pageNum-1, 10, Sort.Direction.ASC, "freeId");
		//첫페이지 10개의 글을 가져온다  
		Page<Freeboard> freeboardPage = freeboardRepository.findAll(pageRequest);
		//freeboardPage를 바로 리스트로 넣었을떄 게시글이 없다면 사이즈가 0인데 Content를 가져와야하기 때문에 에러 발생
		//그래서 if문 넣는다
		
		if(freeboardPage.getSize() == 0) {  
			session.setAttribute("boardList", new ArrayList<Freeboard>()); //Freeboard의 리스트를 boardList라 함 
			session.setAttribute("pageMaker", pagemaker);
			return "freeBoard/freeboard"; // 컨트롤러에서 가져다 쓰는데 조작하기 쉬움  
		}
		
		List<Freeboard> freeboardList = freeboardPage.getContent();
		session.setAttribute("boardList", freeboardList);
		session.setAttribute("pageMaker", pagemaker);
		return "freeBoard/freeboard";
	}
	
}
