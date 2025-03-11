package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.entity.Board;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDTO {
    private Long idBoard;
    private String name;
    private List<TaskDTO> tasks;

    public BoardDTO(Long idBoard, String name, List<TaskDTO> tasks) {
        this.idBoard = idBoard;
        this.name = name;
        this.tasks = tasks;
    }

    public Long getIdBoard() {
        return idBoard;
    }

    public String getName() {
        return name;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public static BoardDTO convertToDTO(Board board) {
        return new BoardDTO(
                board.getIdBoard(),
                board.getName(),
                board.getTasks().stream().map(TaskDTO::convertToDTO).collect(Collectors.toList())
        );
    }

    public static Board convertToEntity(BoardDTO board) {
        return new Board(
                board.getIdBoard(),
                board.getName(),
                board.getTasks().stream().map(TaskDTO::convertToEntity).collect(Collectors.toList())
        );
    }
}
