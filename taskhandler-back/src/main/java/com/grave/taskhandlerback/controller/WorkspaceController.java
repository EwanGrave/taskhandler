package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.WorkspaceDTO;
import com.grave.taskhandlerback.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{idWorkspace}")
    public WorkspaceDTO getWorkspaceById(@PathVariable int idWorkspace) {
        try {
            return workspaceService.getWorkspaceDTOById(idWorkspace);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/user/{idUser}")
    public List<WorkspaceDTO> getWorkspaceByUser(@PathVariable int idUser) {
        try {
            return workspaceService.getWorkspaceDTOByUser(idUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
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

    @PostMapping("/create/membership/{idWorkspace}/{idUser}")
    public ResponseEntity<String> addMembership(@PathVariable int idWorkspace, @PathVariable int idUser) {
        try {
            workspaceService.addMembership(idWorkspace, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Membership added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding membership : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idWorkspace}/{idUser}")
    public ResponseEntity<String> deleteWorkspace(@PathVariable int idWorkspace, @PathVariable int idUser) {
        try {
            workspaceService.deleteWorkspace(idWorkspace, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workspace deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting workspace : " + e.getMessage());
        }
    }

    @PostMapping("/update/{idUser}")
    public ResponseEntity<String> updateWorkspace(@RequestBody WorkspaceDTO workspace, @PathVariable int idUser) {
        try {
            workspaceService.updateWorkspace(workspace, idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workspace updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating workspace : " + e.getMessage());
        }
    }
}
