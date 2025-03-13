package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.entity.Tasklist;

import java.util.List;
import java.util.stream.Collectors;

public class TasklistDTO {
    private Long idTasklist;
    private String name;
    private List<TaskDTO> tasks;

    public TasklistDTO(Long idTasklist, String name, List<TaskDTO> tasks) {
        this.idTasklist = idTasklist;
        this.name = name;
        this.tasks = tasks;
    }

    public Long getIdTasklist() {
        return idTasklist;
    }

    public String getName() {
        return name;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public static TasklistDTO convertToDTO(Tasklist tasklist) {
        return new TasklistDTO(
                tasklist.getIdTasklist(),
                tasklist.getName(),
                tasklist.getTasks().stream().map(TaskDTO::convertToDTO).collect(Collectors.toList())
        );
    }

    public static Tasklist convertToEntity(TasklistDTO tasklist) {
        return new Tasklist(
                tasklist.getIdTasklist(),
                tasklist.getName(),
                tasklist.getTasks().stream().map(TaskDTO::convertToEntity).collect(Collectors.toList())
        );
    }
}
