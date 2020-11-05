package com.thanh.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thanh.boot.controller.request.UserGroupCreateRequest;
import com.thanh.boot.controller.request.UserGroupUpdateRequest;
import com.thanh.boot.controller.response.PaginationDataResponse;
import com.thanh.boot.dto.MenuDTO;
import com.thanh.boot.dto.UserGroupDTO;
import com.thanh.boot.dto.UserGroupDetailsDTO;
import com.thanh.boot.entity.Menu;
import com.thanh.boot.entity.UserGroup;
import com.thanh.boot.mapper.MenuMapper;
import com.thanh.boot.service.UserGroupService;
import com.thanh.boot.util.Constants;
import com.thanh.boot.util.Utils;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(Constants.API)
public class UserGroupController {

    private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

    private UserGroupService userGroupService;
    private MessageSource messageSource;
    private ObjectMapper objectMapper;
    private Validator validator;

    @GetMapping("userGroups")
    public PaginationDataResponse<UserGroupDTO> getUsers(UserGroupDTO params, Pageable pageable) {
        try {
            pageable = Utils.getDefaultSortPageable(pageable);
            Page<UserGroupDTO> dtoPage = userGroupService.search(params, pageable);
            return new PaginationDataResponse<>(dtoPage);
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    @PostMapping("userGroups")
    public ObjectNode create(@Valid @RequestBody UserGroupCreateRequest cmd){
        UserGroup group = userGroupService.create(cmd);
        ObjectNode node = objectMapper.createObjectNode();
        node.put("id", group.getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return node;
    }

    @GetMapping("userGroups/{id}")
    public UserGroupDetailsDTO details(@PathVariable(name = "id") Long id){
        Optional<UserGroupDetailsDTO> optGroup = userGroupService.getDetailsDTOById(id);
        return optGroup.orElse(null);
    }

    @PutMapping("userGroups/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable(name = "id") long groupId,
            @RequestBody UserGroupUpdateRequest cmd){
        cmd.setGroupId(groupId);
        userGroupService.update(cmd);
    }

    @GetMapping("userGroups/{id}/allowedMenus")
    public List<MenuDTO> getAllowedMenus(@PathVariable(name = "id") Long id){
        Optional<UserGroup> optGroup = userGroupService.findByIdWithAllowedMenus(id);
        if (optGroup.isPresent()){
            UserGroup group = optGroup.get();
            Set<Menu> allowedMenus = group.getAllowedMenus();
            List<MenuDTO> result = new ArrayList<>();
            MenuMapper menuMapper = Mappers.getMapper(MenuMapper.class);
            if (allowedMenus != null){
                for (Menu menu : allowedMenus){
                    MenuDTO menuDTO = menuMapper.toMenuDTO(menu);
                    result.add(menuDTO);
                }
            }
            return result;
        } else {
            return null;
        }
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
    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
