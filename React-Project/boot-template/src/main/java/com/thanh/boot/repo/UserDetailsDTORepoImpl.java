package com.thanh.boot.repo;

import com.thanh.boot.dto.UserDetailsDTO;
import com.thanh.boot.entity.User;
import com.thanh.boot.entity.User_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

@Repository
public class UserDetailsDTORepoImpl extends AbstractDetailsRepo<UserDetailsDTO, User, Long> implements UserDetailsDTORepo {

    @Autowired
    public UserDetailsDTORepoImpl(EntityManager em) {
        super(em, UserDetailsDTO.class, User.class);
    }

    @Override
    Selection[] getSelections(Root<User> root) {
        return new Selection[]{
                root.get(User_.ID),
                root.get(User_.USERNAME),
                root.get(User_.EMAIL),
                root.get(User_.PHONE),
                root.get(User_.GIVEN_NAME),
                root.get(User_.SURNAME),
                root.get(User_.LOCALE),
                root.get(User_.TIMEZONE),
                root.get(User_.ENABLED),
                root.join(User_.CREATED_BY, JoinType.LEFT).get(User_.ID),
                root.join(User_.CREATED_BY, JoinType.LEFT).get(User_.GIVEN_NAME),
                root.join(User_.CREATED_BY, JoinType.LEFT).get(User_.SURNAME),
                root.get(User_.CREATED_DATE),
                root.join(User_.LAST_MODIFIED_BY, JoinType.LEFT).get(User_.ID),
                root.join(User_.LAST_MODIFIED_BY, JoinType.LEFT).get(User_.GIVEN_NAME),
                root.join(User_.LAST_MODIFIED_BY, JoinType.LEFT).get(User_.SURNAME),
                root.get(User_.LAST_MODIFIED_DATE),
        };
    }
}
