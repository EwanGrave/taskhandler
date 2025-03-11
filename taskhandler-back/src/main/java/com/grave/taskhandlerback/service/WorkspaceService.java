package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.WorkspaceDTO;
import com.grave.taskhandlerback.entity.Workspace;
import com.grave.taskhandlerback.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceService {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    public List<WorkspaceDTO> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return workspaces.stream().map(WorkspaceDTO::convertToDTO).collect(Collectors.toList());
    }
}
