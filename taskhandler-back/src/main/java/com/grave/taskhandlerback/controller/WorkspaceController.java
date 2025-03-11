package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.WorkspaceDTO;
import com.grave.taskhandlerback.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/workspace", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("all")
    public List<WorkspaceDTO> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }
}
