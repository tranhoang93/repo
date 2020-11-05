package com.thanh.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDTO {
    private Long id;

    private String username;

    private String email;

    private String phone;

    private String givenName;

    private String surname;

    private String locale;

    private String timezone;

    private Boolean enabled;

    private Long createdById;
    private String createdByGivenName;
    private String createdBySurname;
    private LocalDateTime createdDate;

    private Long lastModifiedById;
    private String lastModifiedByGivenName;
    private String lastModifiedBySurname;
    private LocalDateTime lastModifiedDate;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Long id, String username, String email, String phone, String givenName, String surname, String locale, String timezone, Boolean enabled, Long createdById, String createdByGivenName, String createdBySurname, LocalDateTime createdDate, Long lastModifiedById, String lastModifiedByGivenName, String lastModifiedBySurname, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.givenName = givenName;
        this.surname = surname;
        this.locale = locale;
        this.timezone = timezone;
        this.enabled = enabled;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
