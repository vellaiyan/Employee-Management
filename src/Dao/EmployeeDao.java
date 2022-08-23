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
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * The {@code TraineeDao} class implemented to insert, retrive, update, delete
 * employees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Delete functionality using index.
 */

public class EmployeeDao {
    Statement statement;
    Connection connection;
    
    /**
     * {@code insertTrainee} to insert the new trainee in employees list.
     *
     * @param trainee
     *          Trainee object to be insert.
     * 
     * @since 1.0
     *
     */ 
    public boolean insertEmployee(Employee employee) {
    try {
	connection = DriverManager.getConnection("jdbc:mysql://localhost/employeedatabase?verifyServerCertificate=false&useSSL=true","root","VellaiyanI22146");
	statement = connection.createStatement();
        String query = " insert into employee(employee_id, batch, first_name, last_name, subject, gender, date_of_birth, email_id, mobile_number, door_no, city)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, employee.getEmployeeId());
      preparedStatement.setInt(2, employee.getBatch());
      preparedStatement.setString(3, employee.getFirstName());
      preparedStatement.setString(4, employee.getLastName());
      preparedStatement.setString(5, employee.getSubject());
      preparedStatement.setString(6, employee.getGender());
      preparedStatement.setString(7, employee.getDateOfBirth());
      preparedStatement.setString(8, employee.getEmailId());
      preparedStatement.setLong(9, employee.getMobileNumber());
      preparedStatement.setString(10, employee.getDoorNumber());     
      preparedStatement.setString(11, employee.getCity());
      preparedStatement.execute();
    } catch(Exception e) {
        e.printStackTrace();
    }
        employees.add(trainee);
        return true;
    }
}





















