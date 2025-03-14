package com.grave.taskhandlerback.controller;

import com.grave.taskhandlerback.dto.CheckItemDTO;
import com.grave.taskhandlerback.service.CheckitemService;
import com.grave.taskhandlerback.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/checkitem", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckitemController {
    @Autowired
    private CheckitemService checkitemService;

    @PostMapping("/create/{idTask}")
    public ResponseEntity<CheckItemDTO> createCheckitem(@RequestBody CheckItemDTO checkItem, @PathVariable int idTask) {
        try {
            CheckItemDTO newCheckitem = checkitemService.createCheckitem(checkItem, idTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCheckitem);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating check item : " + e.getMessage());
        }
    }

    @PostMapping("/delete/{idCheckitem}")
    public ResponseEntity<ApiResponse> deleteCheckitem(@PathVariable int idCheckitem) {
        try {
            checkitemService.deleteCheckitem(idCheckitem);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Check item deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error while deleting check item : " + e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CheckItemDTO> updateCheckitem(@RequestBody CheckItemDTO checkitem) {
        try {
            CheckItemDTO newCheckitem = checkitemService.updateCheckitem(checkitem);
            return ResponseEntity.status(HttpStatus.OK).body(newCheckitem);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while updating check item : " + e.getMessage());
        }
    }
}
