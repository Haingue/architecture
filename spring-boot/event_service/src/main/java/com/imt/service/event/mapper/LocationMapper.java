package com.imt.service.event.mapper;

import com.imt.service.event.dto.LocationDto;
import com.imt.service.event.entity.LocationEntity;

public class LocationMapper {
    
    public static LocationDto mapToDto (LocationEntity entity) {
        return new LocationDto(
            entity.getAddress(),
            entity.getCapacity()
        );
    }

    public static LocationEntity mapToEntity (LocationDto dto) {
        LocationEntity entity = new LocationEntity();
        entity.setAddress(dto.address());
        entity.setCapacity(dto.capacity());
        return entity;
    }

}
