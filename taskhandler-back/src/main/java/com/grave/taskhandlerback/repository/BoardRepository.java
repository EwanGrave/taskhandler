package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Override
    List<Board> findAll();
}
