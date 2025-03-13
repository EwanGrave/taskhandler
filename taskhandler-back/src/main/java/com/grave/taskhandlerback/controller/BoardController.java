package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.service.BoardService;
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
    public ResponseEntity<String> createBoard(@RequestBody BoardDTO boardDTO) {
        try {
            boardService.createBoard(boardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating Board : " + e.getMessage());
        }
    }

    @PostMapping("/create/membership/{idBoard}/{idUser}")
    public ResponseEntity<String> addMembership(@PathVariable int idBoard, @PathVariable int idUser) {
        try {
            boardService.addMembership(idBoard, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Membership added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding membership : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idBoard}/{idUser}")
    public ResponseEntity<String> deleteBoard(@PathVariable int idBoard, @PathVariable int idUser) {
        try {
            boardService.deleteBoard(idBoard, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting board : " + e.getMessage());
        }
    }

    @PostMapping("/update/{idUser}")
    public ResponseEntity<String> updateBoard(@RequestBody BoardDTO board, @PathVariable int idUser) {
        try {
            boardService.updateBoard(board, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating board : " + e.getMessage());
        }
    }
}
