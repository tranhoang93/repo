package com.thanh.boot.service;


import com.thanh.boot.dto.MenuDTO;
import com.thanh.boot.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService extends BaseService<Menu, Long> {

    Page<MenuDTO> search(MenuDTO params, Pageable pageable);

    List<MenuDTO> getAll();

}
