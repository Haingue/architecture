package com.imt.service.event.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.imt.service.event.entity.LocationEntity;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, String> {
    
}
