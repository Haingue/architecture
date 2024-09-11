package com.imt.service.mark.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imt.service.mark.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    
}
