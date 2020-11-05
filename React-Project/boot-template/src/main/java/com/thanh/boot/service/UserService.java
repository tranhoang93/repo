package com.thanh.boot.service;


import com.thanh.boot.controller.request.UserCreateRequest;
import com.thanh.boot.controller.request.UserUpdateRequest;
import com.thanh.boot.dto.UserDTO;
import com.thanh.boot.dto.UserDetailsDTO;
import com.thanh.boot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findByUsername(String username);

    Page<UserDTO> search(UserDTO params, Pageable pageable);

    Optional<UserDetailsDTO> findUserDetailsDTOById(long userId);

    void update(UserUpdateRequest cmd);

    User create(UserCreateRequest cmd);


}
