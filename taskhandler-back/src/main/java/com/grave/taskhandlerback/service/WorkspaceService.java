package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.WorkspaceDTO;
import com.grave.taskhandlerback.entity.User;
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

    @Autowired
    private UserService userService;

    public List<WorkspaceDTO> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return workspaces.stream().map(WorkspaceDTO::convertToDTO).collect(Collectors.toList());
    }

    public void deleteWorkspace(int idWorkspace) {
        workspaceRepository.deleteById(idWorkspace);
    }

    public void updateWorkspace(WorkspaceDTO workspaceDTO) throws Exception {
        Workspace oldWorkspace = this.getWorkspaceById(workspaceDTO.getIdWorkspace().intValue());
        oldWorkspace.setName(workspaceDTO.getName());
        workspaceRepository.save(oldWorkspace);
    }

    public Workspace getWorkspaceById(int id) throws Exception {
        Workspace workspace = workspaceRepository.findById(id).orElse(null);
        if (workspace == null) {
            throw new Exception("Missing workspace for ID" + id);
        }
        return workspace;
    }

    public void createWorkspace(WorkspaceDTO workspaceDTO) {
        Workspace workspace = WorkspaceDTO.convertToEntity(workspaceDTO);
        workspaceRepository.save(workspace);
    }

    public void addMembership(int idWorkspace, int idUser) throws Exception {
        Workspace workspace = this.getWorkspaceById(idWorkspace);
        User user = userService.getUserById(idUser);

        if (workspace.getUsers().contains(user)) {
            throw new Exception("User already exists in workspace");
        }

        workspace.addUser(user);
        workspaceRepository.save(workspace);
    }
}
