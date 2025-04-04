package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.entity.User;
import com.grave.taskhandlerback.entity.Board;
import com.grave.taskhandlerback.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserService userService;

    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardDTO::convertToDTO).collect(Collectors.toList());
    }

    public BoardDTO getBoardDTOById(int idBoard) throws Exception {
        Board board = this.getBoardById(idBoard);
        return BoardDTO.convertToDTO(board);
    }

    public List<BoardDTO> getBoardDTOByUser(int idUser) throws Exception {
        User user = userService.getUserById(idUser);
        return user.getBoards().stream().map(BoardDTO::convertToDTO).collect(Collectors.toList());
    }

    public void deleteBoard(int idBoard, int idUser) throws Exception {
        User user = userService.getUserById(idUser);
        Board board = this.getBoardById(idBoard);
        if (board.getUsers().get(0) != user) {
            throw new Exception("User has not permission to delete board");
        }
        boardRepository.deleteById(idBoard);
    }

    public BoardDTO updateBoard(BoardDTO boardDTO, int idUser) throws Exception {
        User user = userService.getUserById(idUser);
        Board old = this.getBoardById(boardDTO.getIdBoard().intValue());
        if (old.getUsers().get(0) != user) {
            throw new Exception("User has not permission to update board");
        }
        old.setName(boardDTO.getName());
        return BoardDTO.convertToDTO(boardRepository.save(old));
    }

    public Board getBoardById(int id) throws Exception {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new Exception("Missing board for ID" + id);
        }
        return board;
    }

    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = BoardDTO.convertToEntity(boardDTO);
        return BoardDTO.convertToDTO(boardRepository.save(board));
    }

    public void addMembership(int idBoard, String username) throws Exception {
        Board board = this.getBoardById(idBoard);
        User user = userService.getUserByUsername(username);

        if (board.getUsers().contains(user)) {
            throw new Exception("User already exists in board");
        }

        board.addUser(user);
        boardRepository.save(board);
    }
}
