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

    public WorkspaceDTO getWorkspaceDTOById(int idWorkspace) throws Exception {
        Workspace workspace = this.getWorkspaceById(idWorkspace);
        return WorkspaceDTO.convertToDTO(workspace);
    }

    public List<WorkspaceDTO> getWorkspaceDTOByUser(int idUser) throws Exception {
        User user = userService.getUserById(idUser);
        return user.getWorkspace().stream().map(WorkspaceDTO::convertToDTO).collect(Collectors.toList());
    }

    public void deleteWorkspace(int idWorkspace, int idUser) throws Exception {
        User user = userService.getUserById(idUser);
        Workspace workspace = this.getWorkspaceById(idWorkspace);
        if (workspace.getUsers().get(0) != user) {
            throw new Exception("User has not permission to delete workspace");
        }
        workspaceRepository.deleteById(idWorkspace);
    }

    public void updateWorkspace(WorkspaceDTO workspaceDTO, int idUser) throws Exception {
        User user = userService.getUserById(idUser);
        Workspace oldWorkspace = this.getWorkspaceById(workspaceDTO.getIdWorkspace().intValue());
        if (oldWorkspace.getUsers().get(0) != user) {
            throw new Exception("User has not permission to update workspace");
        }
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
