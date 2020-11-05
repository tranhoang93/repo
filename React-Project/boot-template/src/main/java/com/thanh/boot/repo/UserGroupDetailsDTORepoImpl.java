package com.thanh.boot.repo;

import com.thanh.boot.dto.UserGroupDetailsDTO;
import com.thanh.boot.entity.UserGroup;
import com.thanh.boot.entity.UserGroup_;
import com.thanh.boot.entity.User_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

@Repository
public class UserGroupDetailsDTORepoImpl extends AbstractDetailsRepo<UserGroupDetailsDTO, UserGroup, Long> implements UserGroupDetailsDTORepo {

    @Autowired
    public UserGroupDetailsDTORepoImpl(EntityManager em) {
        super(em, UserGroupDetailsDTO.class, UserGroup.class);
    }

    @Override
    Selection[] getSelections(Root<UserGroup> root) {
        return new Selection[]{
                root.get(UserGroup_.ID),
                root.get(UserGroup_.NAME),
                root.get(UserGroup_.DESCRIPTION),
                root.join(UserGroup_.CREATED_BY, JoinType.LEFT).get(User_.ID),
                root.join(UserGroup_.CREATED_BY, JoinType.LEFT).get(User_.GIVEN_NAME),
                root.join(UserGroup_.CREATED_BY, JoinType.LEFT).get(User_.SURNAME),
                root.get(UserGroup_.CREATED_DATE),
                root.join(UserGroup_.LAST_MODIFIED_BY, JoinType.LEFT).get(User_.ID),
                root.join(UserGroup_.LAST_MODIFIED_BY, JoinType.LEFT).get(User_.GIVEN_NAME),
                root.join(UserGroup_.LAST_MODIFIED_BY, JoinType.LEFT).get(User_.SURNAME),
                root.get(UserGroup_.LAST_MODIFIED_DATE)
        };
    }
}
