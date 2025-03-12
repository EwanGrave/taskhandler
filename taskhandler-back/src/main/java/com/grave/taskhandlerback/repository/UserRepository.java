package com.grave.taskhandlerback.repository;

import com.grave.taskhandlerback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);
}
