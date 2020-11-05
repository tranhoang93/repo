package com.thanh.boot.repo;

import com.thanh.boot.dto.MenuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuDTORepo {
    Page<MenuDTO> search(MenuDTO search, Pageable pageable);
    List<MenuDTO> getAll();
}
