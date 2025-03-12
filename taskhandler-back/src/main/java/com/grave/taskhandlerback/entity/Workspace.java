package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workspace")
    private Long idWorkspace;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "membership",
            joinColumns = @JoinColumn(name = "id_workspace"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<Board> boards;

    public Workspace(Long idWorkspace, String name, List<Board> boards, List<User> users) {
        this.idWorkspace = idWorkspace;
        this.name = name;
        this.boards = boards;
        this.users = users;
    }

    public Workspace() {}

    public Long getIdWorkspace() {
        return idWorkspace;
    }

    public String getName() {
        return name;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) { this.users.add(user); }

    public void setName(String name) {
        this.name = name;
    }
}
