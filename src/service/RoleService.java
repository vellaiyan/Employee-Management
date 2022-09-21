/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.service;

import com.ideas2it.dao.RoleDao;
import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Role;

import java.util.List;

/**
 * The {@code RoleService} class implemented to support add, get, delete, functionalities to role.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 */
public class RoleService {
    RoleDao roleDao = new RoleDao();

    /**
     * {@code addRoles} to add default roles.
     *
     * @throws CustomException.
     *
     * @return addedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean addRoles() throws CustomException {

        return roleDao.insertRoles();
    }

    /**
     * {@code getRolesByRoleName} to get roles by role name.
     *
     * @param roleName
     *       Role name to be get.
     *
     * @throws CustomException.
     *
     * @return roles.
     *
     * @since 1.0
     * 
     */ 
    public List<Role> getRolesByRoleName(String userRole) throws CustomException {

        return roleDao.retrieveRolesByRoleName(userRole);
    }

    /**
     * {@code getRoleByRoleName} to get role by role name.
     *
     * @param roleName
     *       Role name to be get.
     *
     * @throws CustomException.
     *
     * @return role.
     *
     * @since 1.0
     * 
     */ 
    public Role getRoleByRoleName(String userRole) throws CustomException {

        return roleDao.retriveRoleByRoleName(userRole);
    }
}