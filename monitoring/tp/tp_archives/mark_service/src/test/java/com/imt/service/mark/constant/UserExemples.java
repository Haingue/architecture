package com.imt.service.mark.constant;

import java.util.UUID;

public enum UserExemples {
    
    User_1(UUID.randomUUID()),
    User_2(UUID.randomUUID()),
    User_3(UUID.randomUUID());

    private UUID id;

    private UserExemples(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
