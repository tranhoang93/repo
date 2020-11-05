package com.thanh.boot.repo.loginsession;

import com.thanh.boot.entity.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepo extends JpaRepository<LoginSession, Long> {

}
