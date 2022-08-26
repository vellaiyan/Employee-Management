/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import com.ideas2it.exception.CustomException;
 
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
import java.util.List;
import java.util.ArrayList;

public class EmployeeDao extends BaseDao {
    private Connection connection = databaseConnection();
    public int insertEmployee(Employee employee) throws CustomException {
        int employeeId = 0;
        try {
            Date date = Calendar.getInstance().getTime(); 
            PreparedStatement preparedStatement; 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String currentDate = dateFormat.format(date);  
            String query = " insert into employee(batch, first_name, subject, gender, dob, joining_date, email, mobile_no, create_date, modified_date, delete_status)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
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
            preparedStatement.setInt(11, 1);
            preparedStatement.execute();
            String sqlquery = "select last_insert_id()";        
            preparedStatement = connection.prepareStatement(sqlquery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeId = resultSet.getInt("last_insert_id()");
                return employeeId;
            }        

        } catch(Exception e) {
            throw new CustomException(e.getMessage());
        }
        return employeeId;
    }
 
    public List<Employee> retriveEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String query = "select * from employee where delete_status = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int employeeId = resultSet.getInt("id");
                int batch = resultSet.getInt("batch");
                String firstName = resultSet.getString("first_name");
                String subject = resultSet.getString("subject");
                String gender = resultSet.getString("gender");
                String dateOfBirth = resultSet.getString("dob");
                String joiningDate = resultSet.getString("joining_date");
                String emailId = resultSet.getString("email");
                Long mobileNo = resultSet.getLong("mobile_no");
                String createDate = resultSet.getString("create_date");
                String modifiedDate = resultSet.getString("modified_date");
                Employee employee = new Employee(employeeId, batch, firstName, subject, gender, dateOfBirth, joiningDate, 
                    emailId, mobileNo, createDate,modifiedDate);
                employees.add(employee);
            }

        } catch(Exception e) {

        }
        return employees;           
    }

    public List<Employee> retriveEmployeeByrole(int roleId) {
        List<Employee> employees = new ArrayList<Employee>();
        try{
            String sql = "select employee.id, employee.batch, employee.first_name, employee.subject, employee.gender, employee.dob, employee.joining_date, employee.email, employee.mobile_no, employee.create_date, employee.modified_date from employee, employee_roles where employee.id = employee_roles.employee_id and employee_roles.role_id = '" + roleId + "' and delete_status = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("id"));
                employee.setBatch(resultSet.getInt("batch"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setSubject(resultSet.getString("subject"));
                employee.setGender(resultSet.getString("gender"));
                employee.setDateOfBirth(resultSet.getString("dob"));
                employee.setDateOfJoining(resultSet.getString("joining_date"));
                employee.setEmailId(resultSet.getString("email"));
                employee.setMobileNumber(resultSet.getLong("mobile_no"));
                employee.setCreateDate(resultSet.getString("create_date"));
                employee.setUpdateDate(resultSet.getString("modified_date"));
                employees.add(employee);
                              
            }
        } catch (Exception e) {
            
        }
        return employees;
    }  

    public boolean deleteEmployee(String email) {
        try {
            connection = databaseConnection();
            String sql = "update employee set delete_status = 0  where email = '" + email + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            return true;
       } catch (Exception e) {

       } 
       return false;
    }  
    
    public boolean updateEmployeeDetails(Employee employee, String email) {
        try {
            PreparedStatement preparedStatement; 
            Date date = Calendar.getInstance().getTime(); 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String currentDate = dateFormat.format(date);  
            connection = databaseConnection();
            String query = "update employee set batch = ?, first_name = ?, subject = ?, gender = ?, dob = ?,"
                + "joining_date = ?, email = ?, mobile_no = ?, modified_date = ? where email = ?";
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
            preparedStatement.setString(10, email);
            preparedStatement.executeUpdate();
            return true;
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    return false;
    }

    public boolean updateEmployeeDetail(String email, String value, String fieldName) {
        try {
            PreparedStatement preparedStatement;
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String currentDate = dateFormat.format(date);
            connection = databaseConnection();
            String query = "update employee set " + fieldName + " = ?, modified_date = ? where email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, currentDate);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {

        }
        return false;
    }
}





















