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

    public void deleteCheckitem(int idCheckitem) {
        checkitemRepository.deleteById(idCheckitem);
    }

    public CheckItem getCheckitemById(int id) throws Exception {
        CheckItem checkitem = checkitemRepository.findById(id).orElse(null);
        if (checkitem == null) {
            throw new Exception("Missing check item for ID " + id);
        }
        return checkitem;
    }

    public void updateCheckitem(CheckItemDTO checkitem) throws Exception {
        CheckItem oldCheckitem = this.getCheckitemById(checkitem.getIdCheckitem().intValue());
        oldCheckitem.setChecked(checkitem.isChecked());
        oldCheckitem.setDescription(checkitem.getDescription());
        checkitemRepository.save(oldCheckitem);
    }
}
