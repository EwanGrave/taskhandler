package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.service.BoardService;
import com.grave.taskhandlerback.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/board", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/all")
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{idBoard}")
    public BoardDTO getBoardById(@PathVariable int idBoard) {
        try {
            return boardService.getBoardDTOById(idBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/user/{idUser}")
    public List<BoardDTO> getBoardByUser(@PathVariable int idUser) {
        try {
            return boardService.getBoardDTOByUser(idUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        try {
            BoardDTO newBoard = boardService.createBoard(boardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBoard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating Board : " + e.getMessage());
        }
    }

    @PostMapping("/create/membership/{idBoard}/{idUser}")
    public ResponseEntity<ApiResponse> addMembership(@PathVariable int idBoard, @PathVariable int idUser) {
        try {
            boardService.addMembership(idBoard, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Membership added"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while adding membership : " + e.getMessage()));
        }
    }

    @PostMapping("/delete/{idBoard}/{idUser}")
    public ResponseEntity<ApiResponse> deleteBoard(@PathVariable int idBoard, @PathVariable int idUser) {
        try {
            boardService.deleteBoard(idBoard, idUser);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Board deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while deleting board : " + e.getMessage()));
        }
    }

    @PostMapping("/update/{idUser}")
    public ResponseEntity<ApiResponse> updateBoard(@RequestBody BoardDTO board, @PathVariable int idUser) {
        try {
            boardService.updateBoard(board, idUser);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Board updated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while updating board : " + e.getMessage()));
        }
    }
}
