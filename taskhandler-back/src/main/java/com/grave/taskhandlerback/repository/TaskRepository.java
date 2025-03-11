package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Override
    List<Task> findAll();
}
