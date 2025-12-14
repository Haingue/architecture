package com.imt.service.event.constant;

import com.imt.service.event.dto.LocationDto;
import com.imt.service.event.entity.LocationEntity;
import com.imt.service.event.mapper.LocationMapper;

public enum LocationExemples {
    
    Location_1 ("test", 50),
    Location_2 ("test", 100);

    public final String address;
    public final int capacity;

    private LocationExemples(String address, int capacity) {
        this.address = address;
        this.capacity = capacity;
    }

    public LocationDto toDto() {
        return new LocationDto(address, capacity);
    }

    public LocationEntity toEntity () {
        return LocationMapper.mapToEntity(this.toDto());
    }
}
