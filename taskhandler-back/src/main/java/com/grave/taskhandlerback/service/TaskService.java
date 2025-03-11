package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.CheckItemDTO;
import com.grave.taskhandlerback.dto.TaskDTO;
import com.grave.taskhandlerback.entity.Task;
import com.grave.taskhandlerback.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void createTask(TaskDTO taskDTO) {
        Task task = TaskDTO.convertToEntity(taskDTO);
        taskRepository.save(task);
    }

    public void addCheckitem(TaskDTO task, CheckItemDTO checkItem) {
        task.addCheckitem(checkItem);
    }
}
