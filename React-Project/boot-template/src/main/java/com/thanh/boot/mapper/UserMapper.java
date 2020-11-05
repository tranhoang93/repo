package com.thanh.boot.mapper;

import com.thanh.boot.controller.request.UserCreateRequest;
import com.thanh.boot.controller.request.UserUpdateRequest;
import com.thanh.boot.dto.UserDTO;
import com.thanh.boot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {

    default User fromId(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    UserDTO toDto(User user);

    @Mapping(source = "id", target = "id", ignore = true)
    User update(UserUpdateRequest cmd, @MappingTarget User user);

    User create(UserCreateRequest cmd);

}
