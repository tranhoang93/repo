package com.thanh.boot.service.impl;

import com.thanh.boot.controller.request.UserGroupCreateRequest;
import com.thanh.boot.controller.request.UserGroupUpdateRequest;
import com.thanh.boot.dto.UserGroupDTO;
import com.thanh.boot.dto.UserGroupDetailsDTO;
import com.thanh.boot.entity.Menu;
import com.thanh.boot.entity.UserGroup;
import com.thanh.boot.mapper.UserGroupMapper;
import com.thanh.boot.repo.MenuRepo;
import com.thanh.boot.repo.UserGroupDTORepo;
import com.thanh.boot.repo.UserGroupDetailsDTORepo;
import com.thanh.boot.repo.UserGroupRepo;
import com.thanh.boot.service.AbstractService;
import com.thanh.boot.service.UserGroupService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
@Transactional
public class UserGroupServiceImpl extends AbstractService<UserGroup, Long> implements UserGroupService {

    private static final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class);

    private UserGroupDTORepo userGroupDTORepo;
    private UserGroupDetailsDTORepo detailsDTORepo;
    private UserGroupRepo userGroupRepo;
    private MenuRepo menuRepo;

    @Autowired
    public UserGroupServiceImpl(UserGroupRepo userGroupRepo) {
        super(userGroupRepo);
        this.userGroupRepo = userGroupRepo;
    }

    @Override
    public Page<UserGroupDTO> search(UserGroupDTO params, Pageable pageable) {
        return userGroupDTORepo.search(params, pageable);
    }

    @Override
    public UserGroup create(UserGroupCreateRequest cmd) {
        UserGroupMapper mapper = Mappers.getMapper(UserGroupMapper.class);
        UserGroup group = mapper.create(cmd);
        save(group);
        return group;
    }

    @Override
    public Optional<UserGroupDetailsDTO> getDetailsDTOById(long id) {
        return detailsDTORepo.findById(id);
    }

    @Override
    public void update(UserGroupUpdateRequest cmd) {
        long groupId = cmd.getGroupId();
        Optional<UserGroup> optGroup = userGroupRepo.findByIdWithAllowedMenus(groupId);
        if (optGroup.isPresent()) {
            UserGroup group = optGroup.get();
            UserGroupMapper userGroupMapper = Mappers.getMapper(UserGroupMapper.class);
            userGroupMapper.update(cmd, group);
            mergeMenus(group, cmd.getAllowedMenuIds());
        } else {
            throw new IllegalStateException("UserGroup(" + groupId + ") does not exist");
        }
    }

    @Override
    public Optional<UserGroup> findByIdWithAllowedMenus(long userGroupId) {
        return userGroupRepo.findByIdWithAllowedMenus(userGroupId);
    }

    private void mergeMenus(UserGroup group, List<Long> requestMenuIds) {
        logger.debug("Merging menus .... ");
        Set<Menu> groupMenus = group.getAllowedMenus();
        // Which menus are newly added
        if (requestMenuIds != null && requestMenuIds.size() > 0) {
            for (Long requestMenuId : requestMenuIds) {
                if (!isAlreadyInMenuSet(groupMenus, requestMenuId)) {
                    Optional<Menu> optMenu = menuRepo.findById(requestMenuId);
                    if (optMenu.isPresent()) {
                        Menu menu = optMenu.get();
                        logger.debug("Adding new menu to group: Menu ID = {}", requestMenuId);
                        group.addMenu(menu);
                    } else {
                        throw new IllegalStateException("Menu(" + requestMenuId + ") does not exist");
                    }
                }
            }
        }


        // Which ones are removed
        if (groupMenus != null && groupMenus.size() > 0) {
            List<Menu> toRemove = new ArrayList<>();
            for (Menu m : groupMenus) {
                long menuId = m.getId();
                if (!inList(menuId, requestMenuIds)){
                    logger.debug("Marking menu ID = {} to detach from UserGroup", menuId);
                    toRemove.add(m);
                }
            }
            if (toRemove.size() > 0){
                groupMenus.removeAll(toRemove);
            }
        }
    }

    private boolean inList(long id, List<Long> ids){
        if (ids == null || ids.size() == 0){
            return false;
        }
        boolean inList = false;
        for (Long iterateId : ids){
            if (iterateId == id){
                inList = true;
                break;
            }
        }
        return inList;
    }


    private boolean isAlreadyInMenuSet(Set<Menu> groupMenus, long menuId) {
        if (groupMenus == null || groupMenus.size() == 0) {
            return false;
        }
        boolean inSet = false;
        for (Menu m : groupMenus) {
            if (m.getId() == menuId) {
                inSet = true;
                break;
            }
        }
        return inSet;
    }

    @Autowired
    public void setUserGroupDTORepo(UserGroupDTORepo userGroupDTORepo) {
        this.userGroupDTORepo = userGroupDTORepo;
    }

    @Autowired
    public void setDetailsDTORepo(UserGroupDetailsDTORepo detailsDTORepo) {
        this.detailsDTORepo = detailsDTORepo;
    }

    @Autowired
    public void setMenuRepo(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }
}
