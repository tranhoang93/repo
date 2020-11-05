package com.thanh.boot.repo.loginsession;

import com.thanh.boot.dto.LoginSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginSessionDTORepo {
    Page<LoginSessionDTO> search(LoginSessionDTO params, Pageable pageable);
}
