package com.thanh.boot.repo;

import com.thanh.boot.dto.UserGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupDTORepo {
    Page<UserGroupDTO> search(UserGroupDTO search, Pageable pageable);
}
