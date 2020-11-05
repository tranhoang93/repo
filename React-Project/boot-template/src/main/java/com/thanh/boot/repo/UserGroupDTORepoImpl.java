package com.thanh.boot.repo;

import com.thanh.boot.dto.UserGroupDTO;
import com.thanh.boot.entity.UserGroup;
import com.thanh.boot.entity.UserGroup_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserGroupDTORepoImpl extends DynamicSearchRepo<UserGroupDTO, UserGroup> implements UserGroupDTORepo {

    @Autowired
    public UserGroupDTORepoImpl(EntityManager em) {
        super(em, UserGroupDTO.class, UserGroup.class);
    }


    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<UserGroup> root, UserGroupDTO params) {
        List<Predicate> predicates = new ArrayList<>();
        String name = params.getName();
        if (name != null) {
            predicates.add(cb.like(root.get(UserGroup_.NAME), "%" + name.trim() + "%"));
        }
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<UserGroup> root) {
        return new Selection[]{
                root.get(UserGroup_.ID),
                root.get(UserGroup_.NAME),
                root.get(UserGroup_.DESCRIPTION)
        };
    }
}
