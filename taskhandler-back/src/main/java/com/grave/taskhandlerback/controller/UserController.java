package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.dto.user.UserDTOWithPassword;
import com.grave.taskhandlerback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTOWithPassword user) {
        try {
            UserDTO newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating user : " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public Optional<UserDTO> logUser(@RequestBody UserDTOWithPassword userDTO) {
        return userService.logUser(userDTO);
    }
}
