package com.ecommerce.shopcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20 ,  name = "role_name")
    private AppRole appRole;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;



}
