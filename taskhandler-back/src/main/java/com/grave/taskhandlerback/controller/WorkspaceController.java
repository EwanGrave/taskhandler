package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.WorkspaceDTO;
import com.grave.taskhandlerback.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/workspace", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/all")
    public List<WorkspaceDTO> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        try {
            workspaceService.createWorkspace(workspaceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workspace created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating Workspace : " + e.getMessage());
        }
    }
}
