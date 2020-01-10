package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> { 
	//mapper인터페이스랑 같은 역할 
	//User데이터를 삽입, 수정, 삭제하는 역할  | Long : 그 클래스에서 아이디를 어떤 타입으로 지정했느냐 

}
