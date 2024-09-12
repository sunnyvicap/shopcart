package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.RoleDTO;
import com.ecommerce.shopcart.exceptions.ResourceNotFoundException;
import com.ecommerce.shopcart.model.Role;
import com.ecommerce.shopcart.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    public RoleDTO createUserRole(RoleDTO roleDTO) {
        Role role = mapper.map(roleDTO, Role.class);
        return mapper.map(roleRepository.save(role), RoleDTO.class);
    }

    public RoleDTO updateUserRole(RoleDTO roleDTO) {
        Role role = mapper.map(roleDTO, Role.class);
        return mapper.map(roleRepository.save(role), RoleDTO.class);
    }

    public void deleteUserRole(Long id) {
        Role role = getRoleById(id);
        roleRepository.delete(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User roles not found with given id ", "" + id));
    }
}
