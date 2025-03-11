package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;
}
