package com.grave.taskhandlerback.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long idTask;
    private String title;
    private String description;
    @ColumnDefault("false")
    private boolean done;
    @Column()
    private Date dueDate;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<CheckItem> checklist;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "id_task"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="id_tasklist", nullable=false)
    private Tasklist tasklist;

    public Task(Long idTask,
                String title,
                String description,
                List<CheckItem> checklist,
                boolean done,
                Date dueDate,
                List<User> users,
                List<Comment> comments) {
        this.idTask = idTask;
        this.title = title;
        this.description = description;
        this.checklist = checklist;
        this.done = done;
        this.dueDate = dueDate;
        this.users = users;
        this.comments = comments;
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

    public Date getDueDate() {
        return dueDate;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setTasklist(Tasklist tasklist) {
        this.tasklist = tasklist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public List<Comment> getComments() {
        return comments;
    }
}
