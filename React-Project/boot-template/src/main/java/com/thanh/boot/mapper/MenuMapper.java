package com.thanh.boot.mapper;

import com.thanh.boot.dto.MenuDTO;
import com.thanh.boot.entity.Menu;
import org.mapstruct.Mapper;

@Mapper
public interface MenuMapper {

    default Menu fromId(long id) {
        Menu m = new Menu();
        m.setId(id);
        return m;
    }

    MenuDTO toMenuDTO(Menu menu);
}
