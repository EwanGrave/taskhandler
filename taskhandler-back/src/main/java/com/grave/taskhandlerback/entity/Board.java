package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_board")
    private Long idBoard;

    @ManyToOne
    @JoinColumn(name="id_workspace", nullable=false)
    private Workspace workspace;

    @OneToMany(mappedBy = "board")
    private Set<Task> tasks;

    public Long getIdBoard() {
        return idBoard;
    }
}
