package com.thanh.boot.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thanh.boot.validation.constraint.UserLockConstraint;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@UserLockConstraint
public class UserLockRequest {

    private List<Long> userIds;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
