package com.thanh.boot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface BaseService<E extends Serializable, I extends Serializable> {
    void save(E entity);

    void delete(E entity);

    void deleteById(I id);

    Optional<E> findById(I id);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E getOne(I id);
}
