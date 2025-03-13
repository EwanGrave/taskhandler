package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_board")
    private Long idBoard;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "membership",
            joinColumns = @JoinColumn(name = "id_board"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Tasklist> tasklists;

    public Board(Long idBoard, String name, List<Tasklist> tasklists, List<User> users) {
        this.idBoard = idBoard;
        this.name = name;
        this.tasklists = tasklists;
        this.users = users;
    }

    public Board() {}

    public Long getIdBoard() {
        return idBoard;
    }

    public String getName() {
        return name;
    }

    public List<Tasklist> getTasklists() {
        return tasklists;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) { this.users.add(user); }

    public void setName(String name) {
        this.name = name;
    }
}
