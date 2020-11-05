package com.thanh.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanh.boot.dto.MenuDTO;
import com.thanh.boot.service.MenuService;
import com.thanh.boot.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(Constants.API)
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private MessageSource messageSource;
    private ObjectMapper objectMapper;
    private MenuService menuService;

    @GetMapping("menus/all")
    public List<MenuDTO> getAllMenu(){
        return menuService.getAll();
    }

    @GetMapping("menus/mine")
    public List<MenuDTO> getMyMenus(){
        MenuDTO sys= new MenuDTO();
        sys.setCode("System");
        sys.setName("Hệ thống");

        MenuDTO m1 = new MenuDTO();
        m1.setId(1L);
        m1.setCode("UserManagement");
        m1.setName("Người dùng");
        m1.setLevel(1);
        m1.setOrder(0);
        m1.setParentCode(null);


        MenuDTO userGroup = new MenuDTO();
        userGroup.setName("Nhóm người dùng");
        userGroup.setCode("UserGroups");

        sys.addChild(m1);
        sys.addChild(userGroup);
        return Collections.singletonList(sys);
    }


    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
