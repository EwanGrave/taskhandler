package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    @Column(unique=true)
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Board> boards;

    public User(Long idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public User() {}

    public User(Long idUser, String username) {
        this.idUser = idUser;
        this.username = username;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addBoard(Board board) { this.boards.add(board); }

    public List<Board> getBoards() {
        return boards;
    }
}
