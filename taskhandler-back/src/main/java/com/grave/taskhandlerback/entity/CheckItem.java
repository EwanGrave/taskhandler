package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

@Entity
public class CheckItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_checkitem")
    private Long idCheckitem;
    private boolean isChecked;

    @ManyToOne
    @JoinColumn(name="id_task", nullable=false)
    private Task task;

    public CheckItem(Long idCheckitem, boolean isChecked) {
        this.idCheckitem = idCheckitem;
        this.isChecked = isChecked;
    }

    public CheckItem() {}

    public Long getIdCheckitem() {
        return idCheckitem;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
