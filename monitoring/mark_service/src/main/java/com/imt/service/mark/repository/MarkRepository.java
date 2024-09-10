package com.imt.service.mark.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imt.service.mark.entity.MarkEntity;

@Repository
public interface MarkRepository extends CrudRepository<MarkEntity, UUID> {
    
}
