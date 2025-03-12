package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.TaskDTO;
import com.grave.taskhandlerback.entity.Board;
import com.grave.taskhandlerback.entity.Task;
import com.grave.taskhandlerback.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoardService boardService;

    public void createTask(TaskDTO taskDTO, int idBoard) throws Exception {
        Task task = TaskDTO.convertToEntity(taskDTO);
        Board board = boardService.getBoardById(idBoard);
        task.setBoard(board);
        taskRepository.save(task);
    }
}
