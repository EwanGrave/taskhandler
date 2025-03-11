package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
    @Override
    List<Workspace> findAll();
}
