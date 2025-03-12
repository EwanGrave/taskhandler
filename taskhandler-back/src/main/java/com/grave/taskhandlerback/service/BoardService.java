package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.entity.Board;
import com.grave.taskhandlerback.entity.Workspace;
import com.grave.taskhandlerback.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WorkspaceService workspaceService;

    public void createBoard(BoardDTO boardDTO, int idWorkspace) throws Exception {
        Board board = BoardDTO.convertToEntity(boardDTO);
        Workspace workspace = workspaceService.getWorkspaceById(idWorkspace);
        board.setWorkspace(workspace);
        boardRepository.save(board);
    }

    public void deleteBoard(int idBoard) {
        boardRepository.deleteById(idBoard);
    }

    public void updateBoard(BoardDTO boardDTO) throws Exception {
        Board oldBoard = this.getBoardById(boardDTO.getIdBoard().intValue());
        oldBoard.setName(boardDTO.getName());
        boardRepository.save(oldBoard);
    }

    public Board getBoardById(int id) throws Exception {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new Exception("Missing board for ID " + id);
        }
        return board;
    }
}
