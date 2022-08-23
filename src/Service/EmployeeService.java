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
import com.ideas2it.mapper.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TraineeService} class implemented to support add, get, 
 * delete, functionalities to trainees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding default trainees.
 */
   
public class EmployeeService {   
    private EmployeeDao employeeDao = new EmployeeDao();
    private EmployeeMapper employeeMapper = new EmployeeMapper();
   
   /**
     * {@code addTrainee} to pass new trainee to {@code TraineeDao}.
     *
     * @param firstName
     *           First name of trainee.
     *
     * @param lastName 
     *          Last name of trainee.
     *
     * @param id 
     *          Id of trainee.
     * 
     * @param batch
     *          Batch of the trainee.
     *
     * @param date 
     *          Date of birth of the trainee.
     *
     * @param fatherName 
     *          Father name of the trainee.
     *
     * @param motherName 
     *         Mother name of the trainee.
     *
     * @param gender
     *         Gender of the trainee.
     *
     * @param emailId 
     *         Email-id of the trainee.
     *
     * @param mobileNo
     *         Mobile number of the trainee.
     *
     * @param doorNo 
     *         Door number of the trainee.
     *
     * @param city 
     *         City of the trainee.
     *
     * @param taluk 
     *         Taluk of the trainee.
     *
     * @param state 
     *         State of the trainee.
     *
     * @param district 
     *         District of the trainee.
     *
     * @param pinCode 
     *         Pin Code of the trainee. 
     *
     * @since 1.0
     */  
   public boolean addEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        return employeeDao.insertEmployee(employee);

    }
}