package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.CommentDTO;
import com.grave.taskhandlerback.entity.Comment;
import com.grave.taskhandlerback.entity.Task;
import com.grave.taskhandlerback.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskService taskService;

    public CommentDTO createComment(CommentDTO commentDTO, int idtask) throws Exception {
        Comment comment = CommentDTO.convertToEntity(commentDTO);
        Task task = taskService.getTaskById(idtask);
        comment.setTask(task);
        return CommentDTO.convertToDTO(commentRepository.save(comment));
    }
}
