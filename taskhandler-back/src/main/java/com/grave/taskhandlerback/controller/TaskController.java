package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.TaskDTO;
import com.grave.taskhandlerback.service.BoardService;
import com.grave.taskhandlerback.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create/{idBoard}")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO task, @PathVariable int idBoard) {
        try {
            taskService.createTask(task, idBoard);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating task : " + e.getMessage());
        }
    }
}
