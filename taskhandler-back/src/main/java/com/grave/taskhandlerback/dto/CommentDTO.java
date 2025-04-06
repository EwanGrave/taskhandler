package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.dto.user.UserDTO;
import com.grave.taskhandlerback.entity.Comment;

import java.util.Date;

public class CommentDTO {
    private Long idComment;
    private String content;
    private Date createdAt;
    private UserDTO user;

    public CommentDTO(Long idComment, String content, Date createdAt, UserDTO user) {
        this.idComment = idComment;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Long getIdComment() {
        return idComment;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public static CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
                comment.getIdComment(),
                comment.getContent(),
                comment.getCreatedAt(),
                UserDTO.convertToDTO(comment.getUser())
        );
    }

    public static Comment convertToEntity(CommentDTO commentDTO) {
        return new Comment(
                commentDTO.getIdComment(),
                commentDTO.getContent(),
                commentDTO.getCreatedAt(),
                UserDTO.convertToEntity(commentDTO.getUser())
        );
    }
}
