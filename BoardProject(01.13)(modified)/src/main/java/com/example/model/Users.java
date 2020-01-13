package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;
@Data //getter와 setter 자동 생성 
@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	// Users와 Freeboard가 서로 공유하며 자동 증가하기 떄문에
	// 글쓸 때 글번호에 오류가 생긴다. 해결방법 : application.properties에서
	// spring.jpa.hibernate.use-new-id-generator-mappings=false 작성하면서 공유하지안게끔 함
	private Long id;
	private String userid;
	private String password;
	private String username;
	
//Lombok 사용하여 getter & setter이 필요없다.
}
