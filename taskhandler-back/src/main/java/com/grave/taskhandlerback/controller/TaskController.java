package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.TaskDTO;
import com.grave.taskhandlerback.service.TaskService;
import com.grave.taskhandlerback.utils.ApiResponse;
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
    public ResponseEntity<ApiResponse> createTask(@RequestBody TaskDTO task, @PathVariable int idTasklist) {
        try {
            taskService.createTask(task, idTasklist);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Task created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while creating task : " + e.getMessage()));
        }
    }

    @PostMapping("/delete/{idTask}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable int idTask) {
        try {
            taskService.deleteTask(idTask);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Task deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while deleting task : " + e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody TaskDTO task) {
        try {
            taskService.updateTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Task updated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while updating task : " + e.getMessage()));
        }
    }

    @PostMapping("/change_tasklist/{idNewTasklist}")
    public ResponseEntity<ApiResponse> changeTasklist(@PathVariable int idNewTasklist, @RequestBody TaskDTO task) {
        try {
            taskService.changeTasklist(idNewTasklist, task);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Task list updated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while updating task list : " + e.getMessage()));
        }
    }
}
