package com.imt.service.mark.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imt.service.mark.entity.MarkEntity;

@Repository
public interface MarkRepository extends CrudRepository<MarkEntity, UUID> {
    
    @Query("select m.event.id from marktable m group by m.event.id order by avg(m.markValue) desc")
    public Page<UUID> findEventIdOrderByEvaluation (Pageable page);
}
