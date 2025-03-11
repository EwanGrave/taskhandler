package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.entity.Workspace;

import java.util.List;
import java.util.stream.Collectors;

public class WorkspaceDTO {
    private Long idWorkspace;
    private String name;
    private List<BoardDTO> boards;
    private List<UserDTO> users;

    public WorkspaceDTO(Long idWorkspace, String name, List<BoardDTO> boards, List<UserDTO> users) {
        this.idWorkspace = idWorkspace;
        this.name = name;
        this.boards = boards;
        this.users = users;
    }

    public Long getIdWorkspace() {
        return idWorkspace;
    }

    public String getName() {
        return name;
    }

    public List<BoardDTO> getBoards() {
        return boards;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public static WorkspaceDTO convertToDTO(Workspace workspace) {
        return new WorkspaceDTO(
                workspace.getIdWorkspace(),
                workspace.getName(),
                workspace.getBoards().stream().map(BoardDTO::convertToDTO).collect(Collectors.toList()),
                workspace.getUsers().stream().map(UserDTO::convertToDTO).collect(Collectors.toList())
        );
    }

    public static Workspace convertToEntity(WorkspaceDTO workspace) {
        return new Workspace(
                workspace.getIdWorkspace(),
                workspace.getName(),
                workspace.getBoards().stream().map(BoardDTO::convertToEntity).collect(Collectors.toList()),
                workspace.getUsers().stream().map(UserDTO::convertToEntity).collect(Collectors.toList())
        );
    }
}
