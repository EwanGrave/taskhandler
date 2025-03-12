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

    public void deleteTask(int idTask) {
        taskRepository.deleteById(idTask);
    }

    public void updateTask(TaskDTO taskDTO) throws Exception {
        Task oldTask = this.getTaskById(taskDTO.getIdTask().intValue());
        oldTask.setTitle(taskDTO.getTitle());
        oldTask.setDescription(taskDTO.getDescription());
        taskRepository.save(oldTask);
    }

    public Task getTaskById(int id) throws Exception {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new Exception("Missing task for ID " + id);
        }
        return task;
    }
}
