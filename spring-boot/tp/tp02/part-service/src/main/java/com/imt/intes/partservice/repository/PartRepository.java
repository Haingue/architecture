package com.imt.intes.partservice.repository;

import com.imt.intes.partservice.entity.PartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<PartEntity, Long> {
    List<PartEntity> findAllBySupplierCodeOrderById(String supplierCode);
}
