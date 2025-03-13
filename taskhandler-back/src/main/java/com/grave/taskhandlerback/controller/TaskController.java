package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.TaskDTO;
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

    @PostMapping("/create/{idTasklist}")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO task, @PathVariable int idTasklist) {
        try {
            taskService.createTask(task, idTasklist);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating task : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idTask}")
    public ResponseEntity<String> deleteTask(@PathVariable int idTask) {
        try {
            taskService.deleteTask(idTask);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting task : " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody TaskDTO task) {
        try {
            taskService.updateTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating task : " + e.getMessage());
        }
    }

    @PostMapping("/change_tasklist/{idNewTasklist}")
    public ResponseEntity<String> changeTasklist(@PathVariable int idNewTasklist, @RequestBody TaskDTO task) {
        try {
            taskService.changeTasklist(idNewTasklist, task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task list updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating task list : " + e.getMessage());
        }
    }
}
