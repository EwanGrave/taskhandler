package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Override
    List<Comment> findAll();
}
