package com.example.demo.service.impl;

import com.example.demo.dao.entities.Role;
import com.example.demo.dao.enums.RoleType;
import com.example.demo.dao.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Virender Bhargav
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByType(RoleType type) {
        return roleRepository.findByType(type).orElse(null);
    }
}
