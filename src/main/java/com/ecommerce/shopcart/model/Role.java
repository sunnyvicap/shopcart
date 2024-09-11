package com.ecommerce.shopcart.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20 ,  name = "role_name")
    private AppRole appRole;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;



}
