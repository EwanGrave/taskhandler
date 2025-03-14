package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.TaskDTO;
import com.grave.taskhandlerback.entity.Task;
import com.grave.taskhandlerback.entity.Tasklist;
import com.grave.taskhandlerback.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TasklistService tasklistService;

    public TaskDTO createTask(TaskDTO taskDTO, int idTasklist) throws Exception {
        Task task = TaskDTO.convertToEntity(taskDTO);
        Tasklist tasklist = tasklistService.getTasklistById(idTasklist);
        task.setTasklist(tasklist);
        return TaskDTO.convertToDTO(taskRepository.save(task));
    }

    public void deleteTask(int idTask) {
        taskRepository.deleteById(idTask);
    }

    public TaskDTO updateTask(TaskDTO taskDTO) throws Exception {
        Task oldTask = this.getTaskById(taskDTO.getIdTask().intValue());
        oldTask.setTitle(taskDTO.getTitle());
        oldTask.setDescription(taskDTO.getDescription());
        return TaskDTO.convertToDTO(taskRepository.save(oldTask));
    }

    public Task getTaskById(int id) throws Exception {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new Exception("Missing task for ID " + id);
        }
        return task;
    }

    public void changeTasklist(int idNewTasklist, TaskDTO taskDTO) throws Exception {
        Tasklist newTasklist = tasklistService.getTasklistById(idNewTasklist);
        Task task = this.getTaskById(taskDTO.getIdTask().intValue());

        if (newTasklist.getTasks().contains(task)) {
            throw new Exception("Task " + taskDTO.getIdTask() + " is already in the list");
        }

        task.setTasklist(newTasklist);
        taskRepository.save(task);
    }
}
