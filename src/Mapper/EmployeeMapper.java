/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.mapper;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.model.Employee;

/**
 * The {@code EmployeeMapper} class implemented to convert employeeDto into employee and vice versa.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */
public class EmployeeMapper {

    /**
     * {@code fromDto} to convert employeeDto object into employee object.
     *
     * @param employeeDto
     *       EmployeeDto to be converted into Employee.
     *
     * @return employee.
     *
     * @since 1.0
     * 
     */ 
    public Employee fromDto(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getEmployeeId(), employeeDto.getBatch(), employeeDto.getFirstName(),
            employeeDto.getSubject(), employeeDto.getGender(),employeeDto.getDateOfBirth(), employeeDto.getDateOfJoining(),
            employeeDto.getEmailId(), employeeDto.getMobileNumber(), employeeDto.getCreateDate(), employeeDto.getUpdateDate(),                                           employeeDto.getStatus());

        return employee;
    }
   
    /**
     * {@code toDto} to convert employee object into EmployeeDto object.
     *
     * @param employee
     *       Employee to be converted into employeeDto.
     *
     * @return employeeDto.
     *
     * @since 1.0
     * 
     */ 
    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getEmployeeId(), employee.getBatch(), employee.getFirstName(),
            employee.getSubject(), employee.getGender(), employee.getDateOfBirth(), employee.getDateOfJoining(), 
            employee.getEmailId(), employee.getMobileNumber(), employee.getCreateDate(), employee.getModifiedDate(),
            employee.getStatus());

            return employeeDto;
    }
}