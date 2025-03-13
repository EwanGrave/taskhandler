package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.entity.Board;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDTO {
    private Long idBoard;
    private String name;
    private List<TasklistDTO> tasklists;
    private List<UserDTO> users;

    public BoardDTO(Long idBoard, String name, List<TasklistDTO> tasklists, List<UserDTO> users) {
        this.idBoard = idBoard;
        this.name = name;
        this.tasklists = tasklists;
        this.users = users;
    }

    public Long getIdBoard() {
        return idBoard;
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

    public static BoardDTO convertToDTO(Board board) {
        return new BoardDTO(
                board.getIdBoard(),
                board.getName(),
                board.getTasklists().stream().map(TasklistDTO::convertToDTO).collect(Collectors.toList()),
                board.getUsers().stream().map(UserDTO::convertToDTO).collect(Collectors.toList())
        );
    }

    public static Board convertToEntity(BoardDTO board) {
        return new Board(
                board.getIdBoard(),
                board.getName(),
                board.getTasklists().stream().map(TasklistDTO::convertToEntity).collect(Collectors.toList()),
                board.getUsers().stream().map(UserDTO::convertToEntity).collect(Collectors.toList())
        );
    }
}
