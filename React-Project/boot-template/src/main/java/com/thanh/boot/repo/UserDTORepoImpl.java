package com.thanh.boot.repo;

import com.thanh.boot.dto.UserDTO;
import com.thanh.boot.entity.User;
import com.thanh.boot.entity.User_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

@Component
public class UserDTORepoImpl extends DynamicSearchRepo<UserDTO, User> implements UserDTORepo {

    @Autowired
    public UserDTORepoImpl(EntityManager em) {
        super(em, UserDTO.class, User.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<User> root, UserDTO params) {
        return null;
    }

    @Override
    public Selection[] getSelections(Root<User> root) {
        return new Selection[]{
                root.get(User_.ID),
                root.get(User_.USERNAME),
                root.get(User_.EMAIL),
                root.get(User_.PHONE),
                root.get(User_.GIVEN_NAME),
                root.get(User_.SURNAME),
                root.get(User_.LOCALE),
                root.get(User_.TIMEZONE),
                root.get(User_.ENABLED)
        };
    }
}
