package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.RoleDTO;
import com.ecommerce.shopcart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/admin/role")
    public ResponseEntity<RoleDTO> createUserRole(RoleDTO roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createUserRole(roleDTO));
    }

    @PutMapping("/admin/role")
    public ResponseEntity<RoleDTO> updateUserRole(RoleDTO roleDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.createUserRole(roleDTO));
    }

    @DeleteMapping("/admin/role")
    public ResponseEntity<String> deleteRole(Long id) {
        roleService.deleteUserRole(id);
        return ResponseEntity.status(HttpStatus.OK).body("Role deleted from id: " + id);
    }
}
