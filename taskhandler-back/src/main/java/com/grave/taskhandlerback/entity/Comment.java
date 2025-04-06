package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long idComment;
    private String content;
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_task", nullable = false)
    private Task task;

    public Comment(Long idComment, String content, Date createdAt, User user) {
        this.idComment = idComment;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Comment() {}

    public Long getIdComment() {
        return idComment;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
