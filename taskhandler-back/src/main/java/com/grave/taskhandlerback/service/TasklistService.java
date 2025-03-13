package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.TasklistDTO;
import com.grave.taskhandlerback.entity.Tasklist;
import com.grave.taskhandlerback.entity.Workspace;
import com.grave.taskhandlerback.repository.TasklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasklistService {
    @Autowired
    private TasklistRepository tasklistRepository;

    @Autowired
    private WorkspaceService workspaceService;

    public void createTasklist(TasklistDTO tasklistDTO, int idWorkspace) throws Exception {
        Tasklist tasklist = TasklistDTO.convertToEntity(tasklistDTO);
        Workspace workspace = workspaceService.getWorkspaceById(idWorkspace);
        tasklist.setWorkspace(workspace);
        tasklistRepository.save(tasklist);
    }

    public void deleteTasklist(int idTasklist) {
        tasklistRepository.deleteById(idTasklist);
    }

    public void updateTasklist(TasklistDTO tasklistDTO) throws Exception {
        Tasklist old = this.getTasklistById(tasklistDTO.getIdTasklist().intValue());
        old.setName(tasklistDTO.getName());
        tasklistRepository.save(old);
    }

    public Tasklist getTasklistById(int id) throws Exception {
        Tasklist tasklist = tasklistRepository.findById(id).orElse(null);
        if (tasklist == null) {
            throw new Exception("Missing tasklist for ID " + id);
        }
        return tasklist;
    }
}
