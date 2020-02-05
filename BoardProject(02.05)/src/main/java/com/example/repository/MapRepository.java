package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.map;

public interface MapRepository extends JpaRepository<map, Long>{
	map findByMapId(Long mapId);

}
