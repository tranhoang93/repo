package com.thanh.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGroupDetailsDTO {
    private Long id;
    private String name;
    private String description;

    private Long createdById;
    private String createdByGivenName;
    private String createdBySurname;
    private LocalDateTime createdDate;

    private Long lastModifiedById;
    private String lastModifiedByGivenName;
    private String lastModifiedBySurname;
    private LocalDateTime lastModifiedDate;

    public UserGroupDetailsDTO(Long id, String name, String description, Long createdById, String createdByGivenName, String createdBySurname, LocalDateTime createdDate, Long lastModifiedById, String lastModifiedByGivenName, String lastModifiedBySurname, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdById = createdById;
        this.createdByGivenName = createdByGivenName;
        this.createdBySurname = createdBySurname;
        this.createdDate = createdDate;
        this.lastModifiedById = lastModifiedById;
        this.lastModifiedByGivenName = lastModifiedByGivenName;
        this.lastModifiedBySurname = lastModifiedBySurname;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByGivenName() {
        return createdByGivenName;
    }

    public void setCreatedByGivenName(String createdByGivenName) {
        this.createdByGivenName = createdByGivenName;
    }

    public String getCreatedBySurname() {
        return createdBySurname;
    }

    public void setCreatedBySurname(String createdBySurname) {
        this.createdBySurname = createdBySurname;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Long lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public String getLastModifiedByGivenName() {
        return lastModifiedByGivenName;
    }

    public void setLastModifiedByGivenName(String lastModifiedByGivenName) {
        this.lastModifiedByGivenName = lastModifiedByGivenName;
    }

    public String getLastModifiedBySurname() {
        return lastModifiedBySurname;
    }

    public void setLastModifiedBySurname(String lastModifiedBySurname) {
        this.lastModifiedBySurname = lastModifiedBySurname;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
