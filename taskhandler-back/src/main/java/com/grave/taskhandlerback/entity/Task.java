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
    @ElementCollection
    private List<String> checklist;

    @ManyToOne
    @JoinColumn(name="id_board", nullable=false)
    private Board board;

    public Task(Long idTask, String title, String description, List<String> checklist) {
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

    public List<String> getChecklist() {
        return checklist;
    }
}
