package com.example.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.service.freeboard.freeboardInfoService;
import com.example.service.freeboard.freeboardService;
import com.example.service.freeboard.freeboardWriteService;

@Controller
public class freeboardController {
	

	
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
		@GetMapping("/freeBoardInfo")
		public String getPost(@RequestParam(value ="freeId") String freeId) { 
			String page = freeboardInfoService.getFreeboardPost(freeId);
			return page;
		}
		
}
