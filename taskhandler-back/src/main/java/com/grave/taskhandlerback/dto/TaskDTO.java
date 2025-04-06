package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.entity.Task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDTO {
    private Long idTask;
    private String title;
    private String description;
    private boolean done;
    private Date dueDate;
    private List<CheckItemDTO> checkitems;
    private List<UserDTO> users;
    private List<CommentDTO> comments;

    public TaskDTO(Long idTask, String title, String description, List<CheckItemDTO> checkitems, boolean done, Date dueDate, List<UserDTO> users, List<CommentDTO> comments) {
        this.idTask = idTask;
        this.title = title;
        this.description = description;
        this.checkitems = checkitems;
        this.done = done;
        this.dueDate = dueDate;
        this.users = users;
        this.comments = comments;
    }

    public Long getIdTask() {
        return idTask;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<CheckItemDTO> getCheckitems() {
        return checkitems;
    }

    public boolean isDone() {
        return done;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void addCheckitem(CheckItemDTO checkitem) {
        this.checkitems.add(checkitem);
    }

    public List<UserDTO> getUsers() {
        return this.users;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public static TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getIdTask(),
                task.getTitle(),
                task.getDescription(),
                task.getChecklist().stream().map(CheckItemDTO::convertToDTO).collect(Collectors.toList()),
                task.isDone(),
                task.getDueDate(),
                task.getUsers().stream().map(UserDTO::convertToDTO).collect(Collectors.toList()),
                task.getComments().stream().map(CommentDTO::convertToDTO).collect(Collectors.toList())
        );
    }

    public static Task convertToEntity(TaskDTO task) {
        return new Task(
                task.getIdTask(),
                task.getTitle(),
                task.getDescription(),
                task.getCheckitems().stream().map(CheckItemDTO::convertToEntity).collect(Collectors.toList()),
                task.isDone(),
                task.getDueDate(),
                task.getUsers().stream().map(UserDTO::convertToEntity).collect(Collectors.toList()),
                task.getComments().stream().map(CommentDTO::convertToEntity).collect(Collectors.toList())
        );
    }
}
