package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/board", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/create/{idWorkspace}")
    public ResponseEntity<String> createBoard(@RequestBody BoardDTO board, @PathVariable int idWorkspace) {
        try {
            boardService.createBoard(board, idWorkspace);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating board : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idBoard}")
    public ResponseEntity<String> deleteBoard(@PathVariable int idBoard) {
        try {
            boardService.deleteBoard(idBoard);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting board : " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBoard(@RequestBody BoardDTO board) {
        try {
            boardService.updateBoard(board);
            return ResponseEntity.status(HttpStatus.CREATED).body("Board updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating board : " + e.getMessage());
        }
    }
}
