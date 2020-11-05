package com.thanh.boot.service.impl;

import com.thanh.boot.dto.LoginSessionDTO;
import com.thanh.boot.entity.LoginSession;
import com.thanh.boot.repo.loginsession.LoginSessionDTORepo;
import com.thanh.boot.repo.loginsession.LoginSessionRepo;
import com.thanh.boot.service.AbstractService;
import com.thanh.boot.service.LoginSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class LoginSessionServiceImpl extends AbstractService<LoginSession, Long> implements LoginSessionService {

    private LoginSessionDTORepo dtoRepo;
    @Autowired
    public LoginSessionServiceImpl(LoginSessionRepo loginSessionRepo){
        super(loginSessionRepo);
    }

    @Override
    public Page<LoginSessionDTO> search(LoginSessionDTO params, Pageable pageable) {
        return dtoRepo.search(params, pageable);
    }

    @Autowired
    public void setDtoRepo(LoginSessionDTORepo dtoRepo) {
        this.dtoRepo = dtoRepo;
    }
}
