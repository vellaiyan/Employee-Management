/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.mapper;

import com.ideas2it.model.Employee;
import com.ideas2it.dto.EmployeeDto;

public class EmployeeMapper {
     
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getEmployeeId(), employeeDto.getBatch(), employeeDto.getFirstName(), employeeDto.getLastName(), 
            employeeDto.getSubject(), employeeDto.getExperience(), employeeDto.getGender(),employeeDto.getDateOfBirth(), employeeDto.getDateOfJoining(),
            employeeDto.getAge(), employeeDto.getEmailId(), employeeDto.getMobileNumber(), employeeDto.getDoorNumber(), employeeDto.getFatherName(),
            employeeDto.getMotherName(), employeeDto.getCity(), employeeDto.getTaluk(), employeeDto.getDistrict(), employeeDto.getPinCode(),
            employeeDto.getState());
        return employee;
    }
}