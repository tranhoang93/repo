package com.thanh.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.controller.request.UserCreateRequest;
import com.thanh.boot.controller.request.UserLockRequest;
import com.thanh.boot.controller.request.UserUpdateRequest;
import com.thanh.boot.controller.response.PaginationDataResponse;
import com.thanh.boot.controller.response.SuccessResponse;
import com.thanh.boot.dto.UserDTO;
import com.thanh.boot.dto.UserDetailsDTO;
import com.thanh.boot.entity.User;
import com.thanh.boot.service.UserService;
import com.thanh.boot.util.Constants;
import com.thanh.boot.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private MessageSource messageSource;
    private UserService userService;
    private ObjectMapper objectMapper;

    @GetMapping("users")
    public PaginationDataResponse<UserDTO> getUsers(UserDTO params, Pageable pageable) {
        try {
            pageable = Utils.getDefaultSortPageable(pageable);
            Page<UserDTO> dtoPage = userService.search(params, pageable);
            return new PaginationDataResponse<>(dtoPage);
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    @GetMapping("users/{userId}")
    public UserDetailsDTO getUserDetailsDTOById(@PathVariable("userId") Long userId) {
        Optional<UserDetailsDTO> optionalUserDetailsDTO = userService.findUserDetailsDTOById(userId);
        return optionalUserDetailsDTO.orElse(null);
    }

    @PutMapping("users/{userId}")
    public SuccessResponse updateUser(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody UserUpdateRequest cmd
    ) {
        cmd.setId(userId);
        userService.update(cmd);
        String description =
                messageSource.getMessage("updatedSuccessfully", null,
                        LocaleContextHolder.getLocale()
                );

        return new SuccessResponse(description);
    }

    @PostMapping("users")
    public ObjectNode createUser(
            @Valid @RequestBody UserCreateRequest cmd
    ) {
        User user = userService.create(cmd);
        ObjectNode node = objectMapper.createObjectNode();
        node.put("id", user.getId());
        return node;
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteById(userId);
    }

    @PutMapping("users/lock")
    public void lockUsers(@Valid @RequestBody UserLockRequest cmd){

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
