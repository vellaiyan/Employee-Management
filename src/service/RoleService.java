/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service

import com.ideas2it.dao.RoleDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.model.Role;

import java.util.ArrayList;
import java.util.List;


/**
 * The {@code RoleService} class implemented to support add, get, delete, functionalities to Employee.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 */

public class RoleService {

    RoleDao roleDao = new RoleDao();


    /**
     * {@code getRolesByRoleName} to get list of roles using user role.
     *
     * @param userRole
     *       user role of the employees.
     *
     * @return list of roles
     *
     * @since 1.0
     * 
     */ 
    public List<Role> getRolesByRoleName(String userRole) {
        return roleDao.retrieveRolesByRoleName(userRole);
    }


    /**
     * {@code getRoleByRoleName} to get role using role name.
     *
     * @param userRole
     *       Name of the role to be get.
     *
     * @throws CustomException.
     *
     * @return role.
     *
     * @since 1.0
     * 
     */ 
    public Role getRoleByRoleName(String userRole) {
        return roleDao.retrieveRoleByRoleName(userRole);
    }


    /**
     * {@code insertRoles} implemented to insert the default roles.
     *
     *
     *
     * @since 1.0
     * 
     */ 
    public boolean insertRoles() {
        return roleDao.insertRoles();
    }

}






