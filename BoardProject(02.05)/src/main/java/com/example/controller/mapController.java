package com.example.controller;

import java.util.Map;

import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.mapService;

@Controller
public class mapController {
	
	@Autowired
	private mapService mapService;
	
	@PostMapping("/mapPositionRequest")
	public String mapPositionInsert(@RequestParam Map<String, String> paramMap) {
		String x = paramMap.get("xLine"); 
		String y = paramMap.get("yLine");
		String page = mapService.positionInsert(x, y);
		return page; 
	}
}
