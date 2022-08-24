/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dao.RoleDao;
import com.ideas2it.mapper.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;
   
public class EmployeeService {   
    private EmployeeDao employeeDao = new EmployeeDao();
    private EmployeeMapper employeeMapper = new EmployeeMapper();
    private RoleDao roleDao = new RoleDao();
   
    public boolean addEmployee(EmployeeDto employeeDto, String userRole) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        int employeeId = employeeDao.insertEmployee(employee);
        roleDao.assignEmployeeRole(employeeId, userRole);
        return true;
    }
}