package com.example.service;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.map;
import com.example.repository.MapRepository;

@Service
public class mapService {

	
	private Model model;
	
	@Autowired
	private MapRepository mapRepository;
	
	public String positionInsert(String xLine, String yLine) {
		map map = new map();
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDate = LocalDateTime.now().format(dateTimeFormat);
		map.setXLine(xLine);
		map.setYLine(yLine);
		map.setPointDate(currentDate);
		mapRepository.save(map);
		return "map"; 
	}
	
	public map findById(@PathVariable(value = "mapId") Long mapId) { 
		map mapInfo =  mapRepository.findByMapId(mapId);
		model.addAttribute("map", mapInfo);
		return mapInfo;
		 	
	}
	
}
