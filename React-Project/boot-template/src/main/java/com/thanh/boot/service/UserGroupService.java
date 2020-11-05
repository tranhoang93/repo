package com.thanh.boot.service;


import com.thanh.boot.controller.request.UserGroupCreateRequest;
import com.thanh.boot.controller.request.UserGroupUpdateRequest;
import com.thanh.boot.dto.UserGroupDTO;
import com.thanh.boot.dto.UserGroupDetailsDTO;
import com.thanh.boot.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserGroupService extends BaseService<UserGroup, Long> {
    Page<UserGroupDTO> search(UserGroupDTO params, Pageable pageable);
    UserGroup create(UserGroupCreateRequest cmd);
    Optional<UserGroupDetailsDTO> getDetailsDTOById(long id);

    void update(UserGroupUpdateRequest cmd);

    Optional<UserGroup> findByIdWithAllowedMenus(long userGroupId);
}
