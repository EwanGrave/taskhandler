package com.grave.taskhandlerback.service;

import com.grave.taskhandlerback.dto.CheckItemDTO;
import com.grave.taskhandlerback.entity.CheckItem;
import com.grave.taskhandlerback.entity.Task;
import com.grave.taskhandlerback.repository.CheckitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckitemService {
    @Autowired
    private CheckitemRepository checkitemRepository;

    @Autowired
    private TaskService taskService;

    public void createCheckitem(CheckItemDTO checkItemDTO, int idTask) throws Exception {
        CheckItem checkitem = CheckItemDTO.convertToEntity(checkItemDTO);
        Task task = taskService.getTaskById(idTask);
        checkitem.setTask(task);
        checkitemRepository.save(checkitem);
    }
}
