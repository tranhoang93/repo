package com.thanh.boot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.dto.UserDTO;
import com.thanh.boot.entity.User;
import com.thanh.boot.mapper.UserMapper;
import com.thanh.boot.service.UserService;
import com.thanh.boot.util.Constants;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class MeController {

    private MessageSource messageSource;

    private UserService userService;

    @RequestMapping(Constants.API + "/hi")
    @PreAuthorize("hasAuthority('user')")
    public JsonNode sayHi(HttpServletRequest request, Authentication authentication) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        String hello = messageSource.getMessage("hello", null, LocaleContextHolder.getLocale());
        node.put("message", hello + " " + authentication.getPrincipal().toString()
                + ", you are using: " + request.getHeader("User-Agent"));
        return node;
    }

    @GetMapping(Constants.API + "/me/profile")
    public UserDTO getMyProfile(Authentication auth) {
        Long userId = (Long) auth.getDetails();
        Optional<User> optUser = userService.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            UserMapper userMapper = Mappers.getMapper(UserMapper.class);
            return userMapper.toDto(user);
        } else {
            return null;
        }

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
