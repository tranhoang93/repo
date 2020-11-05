package com.thanh.boot.service;


import com.thanh.boot.dto.LoginSessionDTO;
import com.thanh.boot.entity.LoginSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginSessionService extends BaseService<LoginSession, Long> {

    Page<LoginSessionDTO> search(LoginSessionDTO params, Pageable pageable);

}
