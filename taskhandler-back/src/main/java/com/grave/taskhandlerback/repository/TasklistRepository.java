package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.Tasklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasklistRepository extends JpaRepository<Tasklist, Integer> {
    @Override
    List<Tasklist> findAll();
}
