package com.thanh.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDTO {
    private Long id;
    private String code;
    private Integer order;
    private Integer level;
    private String parentCode;
    private String description;
    private String name;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<MenuDTO> children;

    public void addChild(MenuDTO child){
        if (children == null){
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public MenuDTO() {
    }

    public MenuDTO(Long id, String code, Integer order, Integer level, String parentCode, String description) {
        this.id = id;
        this.code = code;
        this.order = order;
        this.level = level;
        this.parentCode = parentCode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
