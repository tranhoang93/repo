package com.thanh.boot.repo;

import com.thanh.boot.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserGroupRepo extends JpaRepository<UserGroup, Long> {

    @Query("select g from UserGroup g left join fetch g.allowedMenus where g.id = :id")
    Optional<UserGroup> findByIdWithAllowedMenus(@Param("id") long id);

}
