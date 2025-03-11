package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.entity.CheckItem;

public class CheckItemDTO {
    private Long idCheckitem;
    private boolean isChecked;
    private String description;

    public CheckItemDTO(Long idCheckitem, boolean isChecked, String description) {
        this.idCheckitem = idCheckitem;
        this.isChecked = isChecked;
        this.description = description;
    }

    public Long getIdCheckitem() {
        return idCheckitem;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getDescription() {
        return description;
    }

    public static CheckItemDTO convertToDTO(CheckItem checkItem) {
        return new CheckItemDTO(checkItem.getIdCheckitem(), checkItem.isChecked(), checkItem.getDescription());
    }

    public static CheckItem convertToEntity(CheckItemDTO checkItem) {
        return new CheckItem(checkItem.getIdCheckitem(), checkItem.isChecked(), checkItem.getDescription());
    }
}
