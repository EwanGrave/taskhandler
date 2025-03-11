package com.grave.taskhandlerback.dto.user;

import com.grave.taskhandlerback.entity.User;

public class UserDTO {
    private Long idUser;
    private String username;

    public UserDTO(Long idUser, String username) {
        this.idUser = idUser;
        this.username = username;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public static UserDTO convertToDTO(User user) {
        return new UserDTO(user.getIdUser(), user.getUsername());
    }

    public static User convertToEntity(UserDTO user) {
        return new User(user.getIdUser(), user.getUsername());
    }
}
