package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.dto.user.UserDTOWithPassword;
import com.grave.taskhandlerback.entity.User;
import com.grave.taskhandlerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("Missing user for name " + username);
        }
        return user;
    }

    public UserDTO createUser(UserDTOWithPassword user) {
        return UserDTO.convertToDTO(userRepository.save(UserDTOWithPassword.convertToEntity(user)));
    }

    public Optional<UserDTO> logUser(UserDTOWithPassword userDTO) {
        User user = userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        return user != null ? Optional.of(UserDTO.convertToDTO(user)) : Optional.empty();
    }
}
