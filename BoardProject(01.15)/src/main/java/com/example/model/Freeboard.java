package com.example.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "freeboard")
public class Freeboard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	// Users와 Freeboard가 서로 공유하며 자동 증가하기 떄문에
	// 글쓸 때 글번호에 오류가 생긴다. 해결방법 : application.properties에서
	// spring.jpa.hibernate.use-new-id-generator-mappings=false 작성하면서 공유하지안게끔 함 
	@Column(name = "freeId")	
	private Long freeId;
	@Column(name = "content")
	private String content;
	@Column(name = "title")
	private String title;
	@Column(name = "writer")
	private String writer;
	@Column(name = "insertDate")
	private String insertDate;
	@Column(name = "updateDate")
	private String updateDate;
	
	
}
