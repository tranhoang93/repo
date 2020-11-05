package com.thanh.boot.service.impl;

import com.thanh.boot.controller.request.UserCreateRequest;
import com.thanh.boot.controller.request.UserUpdateRequest;
import com.thanh.boot.dto.UserDTO;
import com.thanh.boot.dto.UserDetailsDTO;
import com.thanh.boot.entity.User;
import com.thanh.boot.mapper.UserMapper;
import com.thanh.boot.repo.UserDTORepo;
import com.thanh.boot.repo.UserDetailsDTORepo;
import com.thanh.boot.repo.UserRepo;
import com.thanh.boot.service.AbstractService;
import com.thanh.boot.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class UserServiceImpl extends AbstractService<User, Long> implements UserService {

    private UserRepo userRepo;
    private UserDTORepo userDTORepo;
    private UserDetailsDTORepo userDetailsDTORepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        super(userRepo);
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Page<UserDTO> search(UserDTO params, Pageable pageable) {
        return userDTORepo.search(params, pageable);
    }

    @Override
    public Optional<UserDetailsDTO> findUserDetailsDTOById(long userId) {
        return userDetailsDTORepo.findById(userId);
    }

    @Override
    public void update(UserUpdateRequest cmd) {
        User user = userRepo.getOne(cmd.getId());
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        userMapper.update(cmd, user);
    }

    @Override
    public User create(UserCreateRequest cmd) {
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        User user = userMapper.create(cmd);
        //User admin = userMapper.fromId(1L);
        //user.setCreatedBy(admin);
        //user.setLastModifiedBy(admin);
        //user.setCreatedDate(LocalDateTime.now());
        //user.setLastModifiedDate(LocalDateTime.now());
        user.setPassword("1");
        save(user);
        return user;
    }

    @Autowired
    public void setUserDTORepo(UserDTORepo userDTORepo) {
        this.userDTORepo = userDTORepo;
    }

    @Autowired
    public void setUserDetailsDTORepo(UserDetailsDTORepo userDetailsDTORepo) {
        this.userDetailsDTORepo = userDetailsDTORepo;
    }
}
