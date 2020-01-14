package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
	//페이지를 이동하는 컨트롤러
	@GetMapping("/")
	public String index() {
 
		return "index";
	}
}
