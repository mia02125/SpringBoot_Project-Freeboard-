package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "map")
public class map {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long mapId;
	@Column(name = "xLine")
	private String xLine; // 위도(가로선)
	@Column(name = "yLine")
	private String yLine; // 경도(세로선)
	@Column(name = "PointDate")
	private String PointDate;
	
}
