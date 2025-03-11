package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.Set;

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
    private Set<User> users;

    @OneToMany(mappedBy = "workspace")
    private Set<Board> boards;

    public Workspace(Long idWorkspace, String name) {
        this.idWorkspace = idWorkspace;
        this.name = name;
    }

    public Workspace() {}

    public Long getIdWorkspace() {
        return idWorkspace;
    }

    public String getName() {
        return name;
    }
}
