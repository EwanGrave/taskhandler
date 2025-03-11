package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.BoardDTO;
import com.grave.taskhandlerback.service.BoardService;
import com.grave.taskhandlerback.service.WorkspaceService;
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

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping("/create/{idWorkspace}")
    public ResponseEntity<String> createBoard(@RequestBody BoardDTO board, @PathVariable int idWorkspace) {
        try {
            boardService.createBoard(board, workspaceService.getWorkspaceById(idWorkspace));
            return ResponseEntity.status(HttpStatus.CREATED).body("Workspace created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating Workspace : " + e.getMessage());
        }
    }
}
