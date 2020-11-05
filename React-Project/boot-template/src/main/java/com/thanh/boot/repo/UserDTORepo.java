package com.thanh.boot.repo;

import com.thanh.boot.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDTORepo {
    Page<UserDTO> search(UserDTO search, Pageable pageable);
}
