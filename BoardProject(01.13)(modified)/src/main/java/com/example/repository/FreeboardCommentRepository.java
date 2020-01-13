package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.FreeboardComment;

public interface FreeboardCommentRepository extends JpaRepository<FreeboardComment, Long>{

}
