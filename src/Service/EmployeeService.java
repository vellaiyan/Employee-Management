/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.service;

import com.ideas2it.utils.Constants;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Role;
import com.ideas2it.service.RoleService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EmployeeService} class implemented to support add, get, delete, functionalities to Employee.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 * @jls    1.1 Get employee by employee id.
 */
public class EmployeeService {   

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
     * @return addedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean addEmployee(EmployeeDto employeeDto, String userRole) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        RoleService roleService = new RoleService();
        List<Role> roles = roleService.getRolesByRoleName(userRole);
        Employee employee = employeeMapper.fromDto(employeeDto);
        employee.setRoles(roles);
        int employeeId = employeeDao.insertEmployee(employee);

        return false;
    }

    /**
     * {@code geEmployees} to get all employees.
     *
     * @throws CustomException.
     *
     * @return employeeDtos.
     *
     * @since 1.0
     * 
     */ 
    public List<EmployeeDto> getEmployees() throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        List<Employee> employees = employeeDao.retriveEmployees();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();

        for (Employee employee: employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDtos.add(employeeDto);    
        }

        return employeeDtos;    
    }

    /**
     * {@code getEmployeeByRole} to get employees based on the role.
     *
     * @param userRole
     *       Role of the user/employee.
     *
     * @throws CustomException.
     *
     * @return employeeDtos.
     *
     * @since 1.0
     * 
     */ 
    public List<EmployeeDto> getEmployeesByRole(String userRole) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();    
        EmployeeMapper employeeMapper = new EmployeeMapper();
        RoleService roleService = new RoleService();
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        Role role = roleService.getRoleByRoleName(userRole);
        List<Employee> employees = role.getEmployees();

        for (Employee employee: employees) {
            EmployeeDto employeeDto = employeeMapper.toDto(employee);
            employeeDto.setRole(userRole);
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
     * @return checkedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean checkEmployeeById(int employeeId) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        for (Employee employee: employeeDao.retriveEmployees()) { 
            if (employee.getEmployeeId() == employeeId) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@code checkEmployeeByEmailId} to check is emploee emial id is available or not.
     *
     * @param emailId
     *       emailId need to be check..
     *
     * @throws CustomException.
     *
     * @return checkedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean checkEmployeeByEmailId(String emailId) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        for(Employee employee: employeeDao.retriveEmployees()) {
            if (employee.getEmailId().equals(emailId)) {

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
     * @return deletedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        List<Role> role = new ArrayList<Role>();
        Employee employee = employeeDao.retriveEmployeeById(employeeId);
        employee.setStatus(Constants.DELETE_STATUS);
        employee.setRoles(role);
        if (employeeDao.deleteEmployee(employee)) {

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
     * @return updatedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean updateEmployeeDetails(EmployeeDto employeeDto, int employeeId, String userRole) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();        
        EmployeeMapper employeeMapper = new EmployeeMapper();
        RoleService roleService = new RoleService();
        Employee employee = employeeMapper.fromDto(employeeDto);
        Employee employeeFromDatabase = employeeDao.retriveEmployeeById(employeeId);        
        List<Role> roles = roleService.getRolesByRoleName(userRole);
        roles.addAll(employeeFromDatabase.getRoles());
        employee.setRoles(roles);
        employee.setEmployeeId(employeeId);
        employee.setCreateDate(employeeFromDatabase.getCreateDate());

        return employeeDao.updateEmployeeDetails(employee);
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
        EmployeeDao employeeDao = new EmployeeDao();        
        EmployeeMapper employeeMapper = new EmployeeMapper();
        if (checkEmployeeById(employeeId)) {
            Employee employee = employeeDao.retriveEmployeeById(employeeId);

            return employeeMapper.toDto(employeeDao.retriveEmployeeById(employeeId));
        }

       return null;
    }

    /**
     * {@code setUpdatedEmployee} to insert updated employee.
     *
     * @throws CustomException.
     *
     * @return updatedStatus.
     *
     * @since 1.0
     * 
     */     
    public boolean setUpdatedEmployee(EmployeeDto employeeDto) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Employee employee = employeeMapper.fromDto(employeeDto);

        return employeeDao.updateEmployeeDetails(employee);
    }

    /**
     * {@code getEmployeeDetailsById} to get employeeDetails by Id.
     *
     * @param employeeId
     *       Employee need to be get.
     *
     * @throws CustomException.
     *
     * @return employee.
     *
     * @since 1.1
     * 
     */ 
    public Employee getEmployeeDetailsById(int employeeId) throws CustomException {
        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.retriveEmployeeById(employeeId);
    }
}