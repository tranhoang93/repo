package com.thanh.boot.repo;

import com.thanh.boot.dto.MenuDTO;
import com.thanh.boot.entity.Menu;
import com.thanh.boot.entity.Menu_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuDTORepoImpl extends DynamicSearchRepo<MenuDTO, Menu> implements MenuDTORepo {

    @Autowired
    public MenuDTORepoImpl(EntityManager em) {
        super(em, MenuDTO.class, Menu.class);
    }


    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<Menu> root, MenuDTO params) {
        List<Predicate> predicates = new ArrayList<>();
        String code = params.getCode();
        if (code != null) {
            predicates.add(cb.like(root.get(Menu_.CODE), "%" + code + "%"));
        }
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<Menu> root) {
        return new Selection[]{
                root.get(Menu_.ID),
                root.get(Menu_.CODE),
                root.get(Menu_.ORDER),
                root.get(Menu_.LEVEL),
                root.get(Menu_.PARENT_CODE),
                root.get(Menu_.DESCRIPTION)
        };
    }

    @Override
    public List<MenuDTO> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MenuDTO> cq = cb.createQuery(MenuDTO.class);
        Root<Menu> root = cq.from(Menu.class);

        Selection[] selections = getSelections(root);

        cq.select(cb.construct(MenuDTO.class, selections));
        cq.orderBy(
                cb.asc(root.get(Menu_.ORDER)),
                cb.asc(root.get(Menu_.LEVEL)),
                cb.asc(root.get(Menu_.PARENT_CODE))
        );
        TypedQuery<MenuDTO> typeQuery = em.createQuery(cq);

        return typeQuery.getResultList();
    }
}
