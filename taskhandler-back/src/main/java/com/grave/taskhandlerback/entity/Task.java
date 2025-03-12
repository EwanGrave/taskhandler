package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long idTask;
    private String title;
    private String description;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<CheckItem> checklist;

    @ManyToOne
    @JoinColumn(name="id_board", nullable=false)
    private Board board;

    public Task(Long idTask, String title, String description, List<CheckItem> checklist) {
        this.idTask = idTask;
        this.title = title;
        this.description = description;
        this.checklist = checklist;
    }

    public Task() {}

    public Long getIdTask() {
        return idTask;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<CheckItem> getChecklist() {
        return checklist;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
