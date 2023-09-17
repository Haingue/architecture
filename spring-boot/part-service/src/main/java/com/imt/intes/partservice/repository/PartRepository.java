package com.imt.intes.partservice.repository;

import com.imt.intes.partservice.entity.PartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends CrudRepository<PartEntity, Long> {
}
