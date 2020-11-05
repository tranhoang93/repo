package com.thanh.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String phone;

    private String givenName;

    private String surname;

    private String locale;

    private String timezone;

    private Boolean enabled;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String email, String phone, String givenName, String surname, String locale, String timezone, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.givenName = givenName;
        this.surname = surname;
        this.locale = locale;
        this.timezone = timezone;
        this.enabled = enabled;
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
}
