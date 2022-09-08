/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.RoleDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EmployeeService} class implemented to support add, get, delete, functionalities to Employee.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Get employee by employee id.
 */

public class EmployeeService {   
    private EmployeeDao employeeDao = new EmployeeDao();
    private EmployeeMapper employeeMapper = new EmployeeMapper();
    private RoleDao roleDao = new RoleDao();

    public boolean addEmployee(EmployeeDto employeeDto, String userRole) throws CustomException {
        List<Role> roles = roleDao.retrieveRolesByRoleName(userRole);
        Employee employee = employeeMapper.fromDto(employeeDto);
        employee.setRoles(roles);
        int employeeId = employeeDao.insertEmployee(employee);

        return false;
    }

    public List<EmployeeDto> getEmployees() throws CustomException {
        List<Employee> employees = employeeDao.retriveEmployees();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);    
        }

        return employeeDtos;    
    }

    public List<EmployeeDto> getEmployeesByRole(String userRole) throws CustomException {
         List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
         Role role = roleDao.retriveRoleByRoleName(userRole);
         List<Employee> employees = role.getEmployees();
         for (Employee employee : employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDto.setRole(userRole);
            employeeDtos.add(employeeDto);
         }

         return employeeDtos;
    }
    
    public boolean checkEmployeeById(int employeeId) throws CustomException {
        for (Employee employee : employeeDao.retriveEmployees()) { 
            if (employee.getEmployeeId() == employeeId){
                return true;
            }
        }

        return false;
    }
  
    public boolean checkEmployeeByEmailId(String emailId) throws CustomException {
        for(Employee employee : employeeDao.retriveEmployees()) {
            if (employee.getEmailId().equals(emailId)) {
                return true;
            } 
        }

        return false;
    }
 
    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        List<Role> role = new ArrayList<Role>();
        Employee employee = employeeDao.retriveEmployeeById(employeeId);
        employee.setStatus("inactive");
        employee.setRoles(role);
        if (employeeDao.deleteEmployee(employee)) {
            return true;
        }

        return false;
    }

    public boolean updateEmployeeDetails(EmployeeDto employeeDto, int employeeId, String userRole) throws CustomException {
        Employee employee = employeeMapper.fromDto(employeeDto);
        Employee employeeFromDatabase = employeeDao.retriveEmployeeById(employeeId);
        List<Role> roles = roleDao.retrieveRolesByRoleName(userRole);
        roles.addAll(employeeFromDatabase.getRoles());
        employee.setRoles(roles);
        employee.setEmployeeId(employeeId);

        return employeeDao.updateEmployeeDetails(employee);
    }

    public EmployeeDto getEmployeeById(int employeeId) throws CustomException {
       if(checkEmployeeById(employeeId)) {
           Employee employee = employeeDao.retriveEmployeeById(employeeId);
           return employeeMapper.toDto(employeeDao.retriveEmployeeById(employeeId));
       }

       return null;
    }

    public boolean addRoles() throws CustomException {
        return roleDao.insertRoles();
    }
}

