package com.example.demo.service;

import com.example.demo.dao.entities.Role;
import com.example.demo.dao.enums.RoleType;

/**
 * @author Virender Bhargav
 */
public interface RoleService {
    Role findByType(RoleType type);
}
