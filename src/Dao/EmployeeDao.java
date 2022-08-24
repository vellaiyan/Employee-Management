/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import com.ideas2it.model.Employee;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

public class EmployeeDao extends BaseDao {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    int employeeId = 1;
    
    public int insertEmployee(Employee employee) {
        try {
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String currentDate = dateFormat.format(date);  
	    connection = databaseConnection();
            String query = " insert into employee(batch, first_name, subject, gender, dob, joining_date, email, mobile_no, create_date, modified_date)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getBatch());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getSubject());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setString(5, employee.getDateOfBirth());
            preparedStatement.setString(6, employee.getDateOfJoining());
            preparedStatement.setString(7, employee.getEmailId());
            preparedStatement.setLong(8, employee.getMobileNumber());
            preparedStatement.setString(9, currentDate);
            preparedStatement.setString(10, currentDate);
            preparedStatement.execute();
            String sql = "select last_insert_id()";        
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeId = resultSet.getInt("last_insert_id()");
            }        
        return employeeId;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return employeeId;
    }
}





















