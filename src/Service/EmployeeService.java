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
import com.ideas2it.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
   
public class EmployeeService {   
    private EmployeeDao employeeDao = new EmployeeDao();
    private EmployeeMapper employeeMapper = new EmployeeMapper();
    private RoleDao roleDao = new RoleDao();

    public boolean addEmployee(EmployeeDto employeeDto, String userRole) throws CustomException {
        if(isEmployeeAvailable(employeeDto.getEmailId())) {
            System.out.println("Given email id is already exist please choose different Email");
        } else {
            Employee employee = employeeMapper.fromDto(employeeDto);
            int roleId = roleDao.retriveRoleIdByName(userRole);
            int employeeId = employeeDao.insertEmployee(employee);
            return roleDao.assignEmployeeRole(employeeId, roleId);
        }
        return false;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeDao.retriveEmployees();
        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }
        return employees;
    }

    public List<EmployeeDto> getEmployeesByRole(String userRole) {
         int roleId = roleDao.retriveRoleIdByName(userRole);
         List<EmployeeDto> employeesDto = new ArrayList<EmployeeDto>();
         List<Employee> employees = employeeDao.retriveEmployeeByrole(roleId);
         for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeesDto.add(employeeDto);
         }
         return employeesDto;
    }
    
    public boolean isEmployeeAvailable(String emailId) {
        for (Employee employee : employeeDao.retriveEmployees()) {
            if(employee.getEmailId().equals(emailId)){
                return true;
            }
        }
        return false;
    }
 
    public boolean deleteEmployee(String email) {
        if(employeeDao.deleteEmployee(email)) {
            return true;
        }
        return false;
    }
    public boolean updateEmployeeDetails(EmployeeDto employeeDto, String email) {
        Employee employee = employeeMapper.fromDto(employeeDto);
        return employeeDao.updateEmployeeDetails(employee, email);
    }
    
    public boolean updateEmployeeDetail(String email, String value, String fieldName) {
        return employeeDao.updateEmployeeDetail(email, value, fieldName);

    }
}

