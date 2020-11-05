package com.thanh.boot.repo.loginsession;

import com.thanh.boot.dto.LoginSessionDTO;
import com.thanh.boot.entity.LoginSession;
import com.thanh.boot.entity.LoginSession_;
import com.thanh.boot.entity.User;
import com.thanh.boot.entity.User_;
import com.thanh.boot.mapper.UserMapper;
import com.thanh.boot.repo.DynamicSearchRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;


@Repository
public class LoginSessionDTORepoImpl extends DynamicSearchRepo<LoginSessionDTO, LoginSession> implements LoginSessionDTORepo {

    @Autowired
    public LoginSessionDTORepoImpl(EntityManager em) {
        super(em, LoginSessionDTO.class, LoginSession.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<LoginSession> root, LoginSessionDTO params) {
        List<Predicate> predicates = new ArrayList<>();
        Long userId = params.getUserId();
        if (userId != null){
            UserMapper userMapper = Mappers.getMapper(UserMapper.class);
            User user = userMapper.fromId(userId);
            predicates.add(cb.equal(root.get(LoginSession_.USER), user));
        }
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<LoginSession> root) {
        return new Selection[]{
                root.get(LoginSession_.ID),
                root.join(LoginSession_.USER).get(User_.ID),
                root.join(LoginSession_.USER).get(User_.GIVEN_NAME),
                root.join(LoginSession_.USER).get(User_.SURNAME),
                root.get(LoginSession_.USER_AGENT),
                root.get(LoginSession_.IP_ADDRESS),
                root.get(LoginSession_.CREATED_DATE)
        };
    }

}
