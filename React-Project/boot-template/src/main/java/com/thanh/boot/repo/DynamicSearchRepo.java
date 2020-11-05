package com.thanh.boot.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

/**
 * @param <D> DTO class type
 * @param <E> Entity class type
 */
public abstract class DynamicSearchRepo<D, E> {

    private static final Logger logger = LoggerFactory.getLogger(DynamicSearchRepo.class);

    EntityManager em;
    Class<D> dtoClazz;
    Class<E> entityClazz;

    public DynamicSearchRepo(EntityManager em, Class<D> dtoClazz, Class<E> entityClazz) {
        this.em = em;
        this.dtoClazz = dtoClazz;
        this.entityClazz = entityClazz;
    }

    public abstract List<Predicate> getPredicates(CriteriaBuilder cb, Root<E> root, D params);

    public abstract Selection[] getSelections(Root<E> root);

    protected boolean specifyDistinct() {
        return false;
    }

    public Page<D> search(D q, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<D> cq = cb.createQuery(dtoClazz);
        Root<E> root = cq.from(entityClazz);


        List<Predicate> predicates = getPredicates(cb, root, q);


        Selection[] selections = getSelections(root);

        cq.select(cb.construct(dtoClazz, selections));

        if (predicates != null && predicates.size() > 0) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        if (pageable != null) {
            Sort sort = pageable.getSort();
            for (Sort.Order order : sort) {
                if (order.getDirection().isAscending()) {
                    cq.orderBy(cb.asc(root.get(order.getProperty())));
                } else if (order.getDirection().isDescending()) {
                    cq.orderBy(cb.desc(root.get(order.getProperty())));
                }
            }
        }

        if (specifyDistinct()) {
            cq.distinct(true);
        }

        TypedQuery<D> typedQuery = em.createQuery(cq);
        if (pageable != null) {
            typedQuery.setFirstResult((int) pageable.getOffset());
            typedQuery.setMaxResults(pageable.getPageSize());
        }
        List<D> dtoList = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(entityClazz)));
        if (predicates != null && predicates.size() > 0) {
            countQuery.where(cb.and(predicates.toArray(new Predicate[0])));
        }
        if (specifyDistinct()) {
            countQuery.distinct(true);
        }
        long count = em.createQuery(countQuery).getSingleResult();

        Page<D> dtoPage;
        if (pageable == null) {
            dtoPage = new PageImpl<>(dtoList);
        } else {
            dtoPage = new PageImpl<D>(dtoList, pageable, count);
        }
        return dtoPage;
    }
}
