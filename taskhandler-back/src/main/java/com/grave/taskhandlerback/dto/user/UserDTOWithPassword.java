package com.grave.taskhandlerback.dto.user;

import com.grave.taskhandlerback.entity.User;

public class UserDTOWithPassword extends UserDTO {
    private String password;

    public UserDTOWithPassword(Long idUser, String username, String password) {
        super(idUser, username);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static UserDTOWithPassword convertToDTO(User user) {
        return new UserDTOWithPassword(user.getIdUser(), user.getUsername(), user.getPassword());
    }

    public static User convertToEntity(UserDTOWithPassword user) {
        return new User(user.getIdUser(), user.getUsername(), user.getPassword());
    }
}
