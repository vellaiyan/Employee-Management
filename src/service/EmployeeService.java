/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.model.Employee;

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
    private RoleService roleService = new RoleService();

    /**
     * {@code addEmployee} to add new employee.
     *
     * @param employeeDto
     *       EmployeeDto object to be add in the database.
     *
     * @param userRole
     *       Role of the user/employee.
     *
     * @throws CustomException.
     *
     * @return boolean.
     *
     * @since 1.0
     * 
     */ 
    public boolean addEmployee(EmployeeDto employeeDto, String userRole) throws CustomException {
        if (checkEmployeeByEmailId(employeeDto.getEmailId())) {
            System.out.println("Given email id is already exist please choose different Email");
        } else {
            Employee employee = employeeMapper.fromDto(employeeDto);
            int roleId = roleService.getRoleIdByName(userRole);
            int employeeId = employeeDao.insertEmployee(employee);
            return roleDao.assignEmployeeRole(employeeId, roleId);
        }
        return false;
    }

    /**
     * {@code geEmployees} to get all employees.
     *
     * @throws CustomException.
     *
     * @return list of employeeDtos.
     *
     * @since 1.0
     * 
     */ 
    public List<Employee> getEmployees() throws CustomException {
        List<Employee> employees = employeeDao.retriveEmployees();
        for (Employee employee : employees) {
        }
        return employees;
    }

    /**
     * {@code getEmployeeByRole} to get employees based on the role.
     *
     * @param userRole
     *       Role of the user/employee.
     *
     * @throws CustomException.
     *
     * @return List of employeeDtos.
     *
     * @since 1.0
     * 
     */ 
    public List<EmployeeDto> getEmployeesByRole(String userRole) throws CustomException {
         int roleId = roleDao.getRoleIdByName(userRole);
         List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
         List<Employee> employees = employeeDao.retriveEmployeeByRole(roleId);
         for (Employee employee : employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);
         }
         return employeeDtos;
    }
    
    /**
     * {@code checkEmployeeById} to check employee is available or not by project id.
     *
     * @param employeeId
     *       EmployeeId need to be check.
     *
     * @throws CustomException.
     *
     * @return boolean.
     *
     * @since 1.0
     * 
     */ 
    public boolean checkEmployeeById(int employeeId) throws CustomException {
        for (Employee employee : employeeDao.retriveEmployees()) {
            System.out.println(employee.getEmployeeId());
            if(employee.getEmployeeId() == employeeId){
                return true;
            }
        }
        return false;
    }
  
    /**
     * {@code checkEmployeeByEmailId} to check employee is available or not by EmailId
     *
     * @param employeeId
     *       EmployeeId need to be check.
     *
     * @throws CustomException.
     *
     * @return boolean.
     *
     * @since 1.0
     * 
     */ 
    public boolean checkEmployeeByEmailId(String emailId) throws CustomException {
        for(Employee employee : employeeDao.retriveEmployees()) {
            if(employee.getEmailId().equals(emailId)) {
                return true;
            } 
        }
        return false;
    }
 
    /**
     * {@code deleteEmployeeById} to delete employee by employee Id.
     *
     * @param employeeId
     *       Employee id need to be delete.
     *
     * @throws CustomException.
     *
     * @return boolean.
     *
     * @since 1.0
     * 
     */ 
    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        if(employeeDao.deleteEmployeeById(employeeId)) {
            return true;
        }
        return false;
    }


    /**
     * {@code updateEmployeeDetails} to update employee all details.
     *
     * @param empleeDto
     *       EmployeeDto object to be update in the database.
     *
     * @employeeId
     *       Employee id to be update.
     *
     * @param userRole
     *       Role of the user/employee.
     *
     * @throws CustomException.
     *
     * @return boolean.
     *
     * @since 1.0
     * 
    public boolean updateEmployeeDetails(EmployeeDto employeeDto, int employeeId) throws CustomException {
        Employee employee = employeeMapper.fromDto(employeeDto);
        return employeeDao.updateEmployeeDetailsById(employee, employeeId);
    }

    /**
     * {@code updateEmployeeDetaiuls} to update employee details.
     *
     * @param employeeId
     *       Employee id need to be get.
     *
     * @param value 
     *       New updated value.
     *
     * @param fieldName
     *       Field name need to be updated.
     *
     * @throws CustomException.
     *
     * @return employeeDto.
     *
     * @since 1.0
     * 
     */ 
    public boolean updateEmployeeDetail(int employeeId, String value, String fieldName) throws CustomException {
        return employeeDao.updateEmployeeDetailById(employeeId, value, fieldName);

    }


    /**
     * {@code getEmployeeById} to get employee based on the employee id.
     *
     * @param employeeId
     *       Employee id need to be get.
     *
     * @throws CustomException.
     *
     * @return employeeDto.
     *
     * @since 1.0
     * 
     */ 
    public EmployeeDto getEmployeeById(int employeeId) throws CustomException {
       if(checkEmployeeById(employeeId)) {
           Employee employee = employeeDao.retriveEmployeeById(employeeId);
           return employeeMapper.toDto(employeeDao.retriveEmployeeById(employeeId));
       }
       return null;
    }
}

