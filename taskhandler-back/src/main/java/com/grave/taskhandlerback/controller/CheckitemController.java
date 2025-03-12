package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.CheckItemDTO;
import com.grave.taskhandlerback.service.CheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/checkitem", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckitemController {
    @Autowired
    private CheckitemService checkitemService;

    @PostMapping("/create/{idTask}")
    public ResponseEntity<String> createCheckitem(@RequestBody CheckItemDTO checkItem, @PathVariable int idTask) {
        try {
            checkitemService.createCheckitem(checkItem, idTask);
            return ResponseEntity.status(HttpStatus.CREATED).body("Check item created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating check item : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idCheckitem}")
    public ResponseEntity<String> deleteCheckitem(@PathVariable int idCheckitem) {
        try {
            checkitemService.deleteCheckitem(idCheckitem);
            return ResponseEntity.status(HttpStatus.CREATED).body("Check item deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting check item : " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCheckitem(@RequestBody CheckItemDTO checkitem) {
        try {
            checkitemService.updateCheckitem(checkitem);
            return ResponseEntity.status(HttpStatus.CREATED).body("Check item updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating check item : " + e.getMessage());
        }
    }
}
