package com.example.controller;



import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Freeboard;
import com.example.repository.FreeboardRepository;
import com.example.service.freeboard.freeboardInfoService;
import com.example.service.freeboard.freeboardService;
import com.example.service.freeboard.freeboardWriteService;

@Controller
public class freeboardController {
	
	@Autowired
	private FreeboardRepository freeboardRepository;
	 
	@Autowired
	private freeboardService freeboardService;
	
	@Autowired
	private freeboardWriteService freeboardWriteService;
	
	
	
	
	@Autowired
	private freeboardInfoService freeboardInfoService;
	
	private int returnIntValue(String stringToInt) { 
		return Integer.parseInt(stringToInt);
	}
		@GetMapping("/freeboard")
		public String freeboard(@RequestParam(value ="pageNum", defaultValue = "1") String pageNum) { //웹페이지에서 몇page인지 확인하고 아무것도 없으면 1page라고함
		String page = freeboardService.list(returnIntValue(pageNum));		
			return page;
		}
		//글작성하기 위한 컨트롤러 
		@PostMapping("/freeboardWriteRequest")
		public String freeboardWriteRequest(@RequestParam Map<String, String> paramMap) {
			String title = paramMap.get("title");
			String content = paramMap.get("content");
			String writer = paramMap.get("writer");
			String insertDate = paramMap.get("insertDate");
			String updateDate = paramMap.get("updateDate");
		
			freeboardWriteService.write(title, content, writer, insertDate, updateDate);			
			
			return "redirect:/freeboard";
		}
		//글목록창을 위한 컨트롤러
		@GetMapping("/freeBoardInfo")
		public String getPost(@RequestParam(value ="freeId") String freeId) { 
			String page = freeboardInfoService.getFreeboardPost(freeId);
			return page;
		}
		//delete를 위한 컨트롤러
		@RequestMapping(value = "/{freeId}/delete", method = {RequestMethod.GET,RequestMethod.POST}) // GET과 POST
		public String getDelete(@PathVariable Long freeId) {
			Freeboard freeboard = freeboardRepository.findByFreeId(freeId); //Repository에서 Id를 받아온다
			freeboardWriteService.delete(freeboard.getFreeId()); //파라미터값 제대로 가져오자!!!
			//freeboardWriteService로 부터 delete메소드를 받고 freeboard생성자의 FreeId값을 가져온다.
			return "redirect:/freeboard"; // freeboard 창으로 이동 
		}
		//update을 위한 컨트롤러 
		//출처 : https://github.com/beans9/spring-boot-gradle-jpa-web
		@PostMapping(value = "/{freeId}/update")
		public String modify(ModelMap model, @PathVariable Long freeId , Freeboard freeboard, @RequestParam Map<String, String> paramMap) {
			model.addAttribute("freeboardInfo", freeboardRepository.save(freeboard));
			String updateDate = paramMap.get("updateDate");
			String insertDate = paramMap.get("insertDate");
			freeboardWriteService.update(freeId, updateDate, insertDate);
			return "redirect:/freeboard"; 
			//ModelMap : 데이터만 저장 
			
		}
}
