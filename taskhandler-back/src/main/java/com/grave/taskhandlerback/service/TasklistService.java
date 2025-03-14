package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.TasklistDTO;
import com.grave.taskhandlerback.entity.Tasklist;
import com.grave.taskhandlerback.entity.Board;
import com.grave.taskhandlerback.repository.TasklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasklistService {
    @Autowired
    private TasklistRepository tasklistRepository;

    @Autowired
    private BoardService boardService;

    public TasklistDTO createTasklist(TasklistDTO tasklistDTO, int idBoard) throws Exception {
        Tasklist tasklist = TasklistDTO.convertToEntity(tasklistDTO);
        Board board = boardService.getBoardById(idBoard);
        tasklist.setBoard(board);
        return TasklistDTO.convertToDTO(tasklistRepository.save(tasklist));
    }

    public void deleteTasklist(int idTasklist) {
        tasklistRepository.deleteById(idTasklist);
    }

    public TasklistDTO updateTasklist(TasklistDTO tasklistDTO) throws Exception {
        Tasklist old = this.getTasklistById(tasklistDTO.getIdTasklist().intValue());
        old.setName(tasklistDTO.getName());
        return TasklistDTO.convertToDTO(tasklistRepository.save(old));
    }

    public Tasklist getTasklistById(int id) throws Exception {
        Tasklist tasklist = tasklistRepository.findById(id).orElse(null);
        if (tasklist == null) {
            throw new Exception("Missing tasklist for ID " + id);
        }
        return tasklist;
    }
}
