package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.CheckItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckitemRepository extends JpaRepository<CheckItem, Integer> {
    @Override
    List<CheckItem> findAll();
}
