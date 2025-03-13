package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.TasklistDTO;
import com.grave.taskhandlerback.service.TasklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tasklist", produces = MediaType.APPLICATION_JSON_VALUE)
public class TasklistController {
    @Autowired
    private TasklistService tasklistService;

    @PostMapping("/create/{idBoard}")
    public ResponseEntity<String> createTasklist(@RequestBody TasklistDTO tasklist, @PathVariable int idBoard) {
        try {
            tasklistService.createTasklist(tasklist, idBoard);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tasklist created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating tasklist : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idTasklist}")
    public ResponseEntity<String> deleteTasklist(@PathVariable int idTasklist) {
        try {
            tasklistService.deleteTasklist(idTasklist);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tasklist deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting tasklist : " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateTasklist(@RequestBody TasklistDTO tasklist) {
        try {
            tasklistService.updateTasklist(tasklist);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tasklist updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating tasklist : " + e.getMessage());
        }
    }
}
