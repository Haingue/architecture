package com.imt.service.event.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imt.service.event.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    
    Page<EventEntity> findAllByOrderByDatetimeDesc (Pageable page);

}
