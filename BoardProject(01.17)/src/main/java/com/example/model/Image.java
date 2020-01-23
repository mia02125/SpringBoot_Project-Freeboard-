package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Image")
public class Image {
	@Id
	@GeneratedValue
	private Long ImageId;
	private String path;
	private String filename;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private Users users;
	
}
