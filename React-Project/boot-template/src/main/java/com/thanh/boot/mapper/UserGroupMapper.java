package com.thanh.boot.mapper;

import com.thanh.boot.controller.request.UserGroupCreateRequest;
import com.thanh.boot.controller.request.UserGroupUpdateRequest;
import com.thanh.boot.dto.UserGroupDTO;
import com.thanh.boot.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserGroupMapper {

    default UserGroup fromId(long id) {
        UserGroup group = new UserGroup();
        group.setId(id);
        return group;
    }

    UserGroupDTO toDTO(UserGroup userGroup);

    UserGroup create(UserGroupCreateRequest cmd);

    @Mapping(source = "groupId", target = "id", ignore = true)
    UserGroup update(UserGroupUpdateRequest cmd, @MappingTarget UserGroup group);

}
