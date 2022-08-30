/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
import java.time.LocalDate;

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
        if(checkEmployeeByEmailId(employeeDto.getEmailId())) {
            System.out.println("Given email id is already exist please choose different Email");
        } else {
            Employee employee = employeeMapper.fromDto(employeeDto);
            int roleId = roleDao.retriveRoleIdByName(userRole);
            int employeeId = employeeDao.insertEmployee(employee);
            return roleDao.assignEmployeeRole(employeeId, roleId);
        }
        return false;
    }

    public List<Employee> getEmployees() throws CustomException {
        List<Employee> employees = employeeDao.retriveEmployees();
        for (Employee employee : employees) {
        }
        return employees;
    }

    public List<EmployeeDto> getEmployeesByRole(String userRole) throws CustomException {
         int roleId = roleDao.retriveRoleIdByName(userRole);
         List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
         List<Employee> employees = employeeDao.retriveEmployeeByRole(roleId);
         for (Employee employee : employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
         }
         return employeeDtos;
    }
    
    public boolean checkEmployeeById(int employeeId) throws CustomException {
        for (Employee employee : employeeDao.retriveEmployees()) {
            System.out.println(employee.getEmployeeId());
            if(employee.getEmployeeId() == employeeId){
                return true;
            }
        }
        return false;
    }
  
    public boolean checkEmployeeByEmailId(String emailId) throws CustomException {
        for(Employee employee : employeeDao.retriveEmployees()) {
            if(employee.getEmailId().equals(emailId)) {
                return true;
            } 
        }
        return false;
    }
 
    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        if(employeeDao.deleteEmployeeById(employeeId)) {
            return true;
        }
        return false;
    }
    public boolean updateEmployeeDetails(EmployeeDto employeeDto, int employeeId) throws CustomException {
        Employee employee = employeeMapper.fromDto(employeeDto);
        return employeeDao.updateEmployeeDetailsById(employee, employeeId);
    }
    
    public boolean updateEmployeeDetail(int employeeId, String value, String fieldName) throws CustomException {
        return employeeDao.updateEmployeeDetailById(employeeId, value, fieldName);

    }

    public EmployeeDto getEmployeeById(int employeeId) throws CustomException {
       if(checkEmployeeById(employeeId)) {
           Employee employee = employeeDao.retriveEmployeeById(employeeId);
           return employeeMapper.toDto(employeeDao.retriveEmployeeById(employeeId));
       }
       return null;
    }
}

