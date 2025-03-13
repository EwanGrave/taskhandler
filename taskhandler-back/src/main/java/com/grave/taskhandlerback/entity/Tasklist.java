package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tasklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tasklist")
    private Long idTasklist;
    private String name;

    @ManyToOne
    @JoinColumn(name="id_board", nullable=false)
    private Board board;

    @OneToMany(mappedBy = "tasklist", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Tasklist(Long idTasklist, String name, List<Task> tasks) {
        this.idTasklist = idTasklist;
        this.name = name;
        this.tasks = tasks;
    }

    public Tasklist() {}

    public Long getIdTasklist() {
        return idTasklist;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setName(String name) {
        this.name = name;
    }
}
