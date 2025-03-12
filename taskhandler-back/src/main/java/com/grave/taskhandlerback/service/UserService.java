package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.entity.User;
import com.grave.taskhandlerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) throws Exception {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new Exception("Missing user for ID " + id);
        }
        return user;
    }
}
