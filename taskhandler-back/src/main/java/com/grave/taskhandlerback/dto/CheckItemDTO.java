package com.grave.taskhandlerback.dto;

import com.grave.taskhandlerback.entity.CheckItem;

public class CheckItemDTO {
    private Long idCheckitem;
    private boolean checked;
    private String description;

    public CheckItemDTO(Long idCheckitem, boolean checked, String description) {
        this.idCheckitem = idCheckitem;
        this.checked = checked;
        this.description = description;
    }

    public Long getIdCheckitem() {
        return idCheckitem;
    }

    public boolean isChecked() {
        return checked;
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
