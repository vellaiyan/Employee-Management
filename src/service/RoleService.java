/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service;

import com.ideas2it.model.Role;
import com.ideas2it.Dao.RoleDao;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RoleService} class implemented to support add, get, delete, functionalities for Role.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 */

public class RoleService {

    private RoleDao roleDao = new RoleDao();

    public int getRoleIdByName(String userRole) {
        return roleDao.retriveRoleIdByName(userRole);
    }

    
