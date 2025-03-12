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

    @ManyToOne
    @JoinColumn(name="id_workspace", nullable=false)
    private Workspace workspace;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Board(Long idBoard, String name, List<Task> tasks) {
        this.idBoard = idBoard;
        this.name = name;
        this.tasks = tasks;
    }

    public Board() {}

    public Long getIdBoard() {
        return idBoard;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }
}
