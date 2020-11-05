package com.thanh.boot.controller;


import com.thanh.boot.controller.response.PaginationDataResponse;
import com.thanh.boot.dto.LoginSessionDTO;
import com.thanh.boot.service.LoginSessionService;
import com.thanh.boot.util.Constants;
import com.thanh.boot.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API)
public class LoginSessionController {
    private LoginSessionService loginSessionService;

    @GetMapping("loginSessions")
    public PaginationDataResponse<LoginSessionDTO> getPageLoginSessions(LoginSessionDTO params, Pageable pageable){
        pageable = Utils.getDefaultSortPageable(pageable);
        Page<LoginSessionDTO> dtoPage = loginSessionService.search(params, pageable);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new PaginationDataResponse<>(dtoPage);
    }

    @Autowired
    public void setLoginSessionService(LoginSessionService loginSessionService) {
        this.loginSessionService = loginSessionService;
    }
}
