package com.imt.service.mark.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imt.service.mark.entity.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, UUID> {
    
}
