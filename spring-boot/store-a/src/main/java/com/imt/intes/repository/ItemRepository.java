package com.imt.intes.repository;

import com.imt.intes.model.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

    Optional<ItemEntity> findFirstByName (String name);
    Page<ItemEntity> findAllByOrderByName (Pageable page);
    Page<ItemEntity> findAllByNameLikeOrderByName (String nameFilter, Pageable page);
}
