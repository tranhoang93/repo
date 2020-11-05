package com.thanh.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginSessionDTO {
    private long id;

    private Long userId;

    private String userGivenName;

    private String userSurname;

    private String userAgent;

    private String ipAddress;

    private LocalDateTime createdDate;

    public LoginSessionDTO() {
    }

    public LoginSessionDTO(long id, Long userId, String userGivenName, String userSurname, String userAgent, String ipAddress, LocalDateTime createdDate) {
        this.id = id;
        this.userId = userId;
        this.userGivenName = userGivenName;
        this.userSurname = userSurname;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserGivenName() {
        return userGivenName;
    }

    public void setUserGivenName(String userGivenName) {
        this.userGivenName = userGivenName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
