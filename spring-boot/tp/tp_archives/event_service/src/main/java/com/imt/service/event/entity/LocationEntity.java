package com.imt.service.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LocationEntity {
    @Id
    private String address;
    private int capacity;

    public LocationEntity () {
        super();
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + capacity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocationEntity other = (LocationEntity) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (capacity != other.capacity)
            return false;
        return true;
    }

    
}
