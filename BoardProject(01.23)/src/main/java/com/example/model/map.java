package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "mapInfo")
public class map {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long mapId;
	private String Lat; // 위도(가로선)
	private String Lng; // 경도(세로선)
	private String PointTime;
}
