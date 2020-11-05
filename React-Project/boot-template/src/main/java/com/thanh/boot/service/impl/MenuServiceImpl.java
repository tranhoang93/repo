package com.thanh.boot.service.impl;

import com.thanh.boot.dto.MenuDTO;
import com.thanh.boot.entity.Menu;
import com.thanh.boot.repo.MenuDTORepo;
import com.thanh.boot.repo.MenuRepo;
import com.thanh.boot.service.AbstractService;
import com.thanh.boot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class MenuServiceImpl extends AbstractService<Menu, Long> implements MenuService {

    private MenuDTORepo menuDTORepo;

    @Autowired
    public MenuServiceImpl(MenuRepo menuRepo) {
        super(menuRepo);
    }


    @Override
    public Page<MenuDTO> search(MenuDTO params, Pageable pageable) {
        return menuDTORepo.search(params, pageable);
    }

    @Override
    public List<MenuDTO> getAll() {
        List<MenuDTO> allMenus = menuDTORepo.getAll();
        List<MenuDTO> level1Menus = new ArrayList<>();
        if (allMenus.size() > 0) {
            // List<Integer> toRemoveLevel1 = new ArrayList<>();
            int index = 0;
            for (MenuDTO m : allMenus) {
                if (m.getLevel() == 1) {
                    level1Menus.add(m);
                    //toRemoveLevel1.add(index);
                }

                index++;
            }
            //if (toRemoveLevel1.size() > 0) {
            //    remove(allMenus, toRemoveLevel1);
            //}


            // Add all level 2 to level 1
            if (level1Menus.size() > 0) {
                //List<Integer> toRemoveLevel2 = new ArrayList<>();
                for (MenuDTO m1 : level1Menus) {
                    int indexLevel2 = 0;
                    for (MenuDTO iterateMenu : allMenus) {
                        if (m1.getCode().equals(iterateMenu.getParentCode())) {
                            m1.addChild(iterateMenu);
                        }
                        indexLevel2++;
                    }
                }
                // remove(allMenus, toRemoveLevel2);
            }

            // Finally return list of level1 menus,
            // which include all level2 and level3 menus
            return level1Menus;
        } else {
            return allMenus;
        }
    }

    private List<Integer> reverse(List<Integer> originalList) {
        if (originalList == null) {
            return null;
        }
        if (originalList.isEmpty()) {
            return originalList;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = originalList.size() - 1; i > 0; i--) {
            result.add(originalList.get(i));
        }
        return result;
    }

    private void remove(List<MenuDTO> allMenus, List<Integer> toRemoveIndexes) {
        if (allMenus == null || allMenus.isEmpty() || toRemoveIndexes == null || toRemoveIndexes.isEmpty()){
            return;
        }
        toRemoveIndexes = reverse(toRemoveIndexes);
        for (Integer rmIndex : toRemoveIndexes) {
            allMenus.remove(rmIndex.intValue());
        }
    }

    @Autowired
    public void setMenuDTORepo(MenuDTORepo menuDTORepo) {
        this.menuDTORepo = menuDTORepo;
    }
}
