package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadImageController {
	
	@PostMapping("/uploadImage")
	public String UploadImage(@RequestParam("imageFile") MultipartFile imageFile) { 
		String returnValue = "";
		
		
		return returnValue;
	}
}
