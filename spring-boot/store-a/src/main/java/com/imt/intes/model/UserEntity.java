package com.imt.intes.model;

import com.imt.intes.configuration.security.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class UserEntity {

    @Id
    private String login;
    @Column(nullable = false)
    private String password;
    @Column
    private String address;
    @Column
    private boolean isEnable;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderEntity> orders;

    public UserEntity() {
        this.orders = new HashSet<>();
        this.roles = new HashSet<>();
    }

}
