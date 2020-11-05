package com.thanh.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private long accessTokenLifeTimeMinutes = 5;
    private long refreshTokenLifeTimeMinutes = 60;

    public long getAccessTokenLifeTimeMinutes() {
        return accessTokenLifeTimeMinutes;
    }

    public void setAccessTokenLifeTimeMinutes(long accessTokenLifeTimeMinutes) {
        this.accessTokenLifeTimeMinutes = accessTokenLifeTimeMinutes;
    }

    public long getRefreshTokenLifeTimeMinutes() {
        return refreshTokenLifeTimeMinutes;
    }

    public void setRefreshTokenLifeTimeMinutes(long refreshTokenLifeTimeMinutes) {
        this.refreshTokenLifeTimeMinutes = refreshTokenLifeTimeMinutes;
    }
}
