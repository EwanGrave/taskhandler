package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.dto.WorkspaceDTO;
import com.grave.taskhandlerback.entity.Board;
import com.grave.taskhandlerback.entity.Workspace;
import com.grave.taskhandlerback.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void createBoard(BoardDTO boardDTO, WorkspaceDTO workspaceDTO) {
        Workspace workspace = WorkspaceDTO.convertToEntity(workspaceDTO);
        Board board = BoardDTO.convertToEntity(boardDTO);
        board.setWorkspace(workspace);
        boardRepository.save(board);
    }
}
