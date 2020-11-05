package com.thanh.boot.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGroupUpdateRequest {
    private Long groupId;
    private String name;
    private String description;
    private List<Long> allowedMenuIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getAllowedMenuIds() {
        return allowedMenuIds;
    }

    public void setAllowedMenuIds(List<Long> allowedMenuIds) {
        this.allowedMenuIds = allowedMenuIds;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
