package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.entity.Workspace;

import java.util.List;
import java.util.stream.Collectors;

public class WorkspaceDTO {
    private Long idWorkspace;
    private String name;
    private List<TasklistDTO> tasklists;
    private List<UserDTO> users;

    public WorkspaceDTO(Long idWorkspace, String name, List<TasklistDTO> tasklists, List<UserDTO> users) {
        this.idWorkspace = idWorkspace;
        this.name = name;
        this.tasklists = tasklists;
        this.users = users;
    }

    public Long getIdWorkspace() {
        return idWorkspace;
    }

    public String getName() {
        return name;
    }

    public List<TasklistDTO> getTasklists() {
        return tasklists;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public static WorkspaceDTO convertToDTO(Workspace workspace) {
        return new WorkspaceDTO(
                workspace.getIdWorkspace(),
                workspace.getName(),
                workspace.getTasklists().stream().map(TasklistDTO::convertToDTO).collect(Collectors.toList()),
                workspace.getUsers().stream().map(UserDTO::convertToDTO).collect(Collectors.toList())
        );
    }

    public static Workspace convertToEntity(WorkspaceDTO workspace) {
        return new Workspace(
                workspace.getIdWorkspace(),
                workspace.getName(),
                workspace.getTasklists().stream().map(TasklistDTO::convertToEntity).collect(Collectors.toList()),
                workspace.getUsers().stream().map(UserDTO::convertToEntity).collect(Collectors.toList())
        );
    }
}
