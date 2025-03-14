package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.TasklistDTO;
import com.grave.taskhandlerback.service.TasklistService;
import com.grave.taskhandlerback.utils.ApiResponse;
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
    public ResponseEntity<ApiResponse> createTasklist(@RequestBody TasklistDTO tasklist, @PathVariable int idBoard) {
        try {
            tasklistService.createTasklist(tasklist, idBoard);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Tasklist created"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while creating tasklist : " + e.getMessage()));
        }
    }

    @PostMapping("/delete/{idTasklist}")
    public ResponseEntity<ApiResponse> deleteTasklist(@PathVariable int idTasklist) {
        try {
            tasklistService.deleteTasklist(idTasklist);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Tasklist deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while deleting tasklist : " + e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateTasklist(@RequestBody TasklistDTO tasklist) {
        try {
            tasklistService.updateTasklist(tasklist);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Tasklist updated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while updating tasklist : " + e.getMessage()));
        }
    }
}
