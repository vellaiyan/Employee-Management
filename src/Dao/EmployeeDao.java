/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;

/**
 * The {@code EmployeeDao} class implemented to insert, retrive, update, delete all employees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Retrive empoyee by EmployeeId.
 */

public class EmployeeDao extends BaseDao {
    private Connection connection = databaseConnection();

    public int insertEmployee(Employee employee) throws CustomException {
        try {
            Date date = new Date(0);
            PreparedStatement preparedStatement;
            String query = " insert into employee(batch, first_name, subject, gender, dob, joining_date, email, mobile_no, status)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, 'active')"; 
            preparedStatement = connection.prepareStatement(query); 
            preparedStatement.setInt(1, employee.getBatch());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getSubject());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setDate(5, date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setDate(6, date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setString(7, employee.getEmailId());
            preparedStatement.setLong(8, employee.getMobileNumber());
            preparedStatement.execute();            
            return getLastInsertId(preparedStatement);
 
        } catch(SQLException e) {
            throw new CustomException(e.getMessage());
        }
    }
 
    public List<Employee> retriveEmployees() throws CustomException {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String query = "select * from  employee where status = 'active'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("id"));
                employee.setBatch(resultSet.getInt("batch"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setSubject(resultSet.getString("subject"));
                employee.setGender(resultSet.getString("gender"));
                employee.setDateOfBirth(resultSet.getDate("dob").toLocalDate());
                employee.setDateOfJoining(resultSet.getDate("joining_date").toLocalDate());
                employee.setEmailId(resultSet.getString("email"));
                employee.setMobileNumber(resultSet.getLong("mobile_no"));
                employee.setCreateDate(resultSet.getDate("created_date").toLocalDate());
                employee.setUpdateDate(resultSet.getDate("modified_date").toLocalDate());
                employees.add(employee);
            }

        } catch(SQLException e) {
            throw new CustomException(e.getMessage());
        }
        return employees;           
    }

    public List<Employee> retriveEmployeeByRole(int roleId) throws CustomException {
        List<Employee> employees = new ArrayList<Employee>();
        try{
            String sql = "select employee.id, employee.batch, employee.first_name, employee.subject, employee.gender,"
                + "employee.dob, employee.joining_date, employee.email, employee.mobile_no, employee.created_date,"
                + "employee.modified_date from employee, employee_roles where employee.id = employee_roles.employee_id"
                + "and employee_roles.role_id = '" + roleId + "' and status = 'active'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Employee employee = new Employee(); 
                employee.setEmployeeId(resultSet.getInt("id"));
                employee.setBatch(resultSet.getInt("batch"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setSubject(resultSet.getString("subject"));
                employee.setGender(resultSet.getString("gender"));
                employee.setDateOfBirth(resultSet.getDate("dob").toLocalDate());
                employee.setDateOfJoining(resultSet.getDate("joining_date").toLocalDate());
                employee.setEmailId(resultSet.getString("email"));
                employee.setMobileNumber(resultSet.getLong("mobile_no"));
                employee.setCreateDate(resultSet.getDate("created_date").toLocalDate());
                employee.setUpdateDate(resultSet.getDate("modified_date").toLocalDate());
                employees.add(employee);
                              
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }
        return employees;
    }  

    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        try {
            connection = databaseConnection();
            String sql = "update employee set status = 'inactive'  where id = '" + employeeId + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            return true;
       } catch (SQLException e) {
            throw new CustomException(e.getMessage());
       } 
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }  
    
    public boolean updateEmployeeDetailsById(Employee employee, int employeeId) throws CustomException {
        try {
            PreparedStatement preparedStatement;  
            connection = databaseConnection();
            Date date = new Date(0);
            String query = "update employee set batch = ?, first_name = ?, subject = ?, gender = ?, dob = ?,"
                + "joining_date = ?, email = ?, mobile_no = ?, modified_date = current_timestamp where id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getBatch());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getSubject());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setDate(5, date.valueOf(employee.getDateOfBirth())); 
            preparedStatement.setDate(6, date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setString(7, employee.getEmailId());
            preparedStatement.setLong(8, employee.getMobileNumber());
            preparedStatement.setInt(9, employeeId);
            preparedStatement.executeUpdate();
            return true;
        } catch(SQLException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public boolean updateEmployeeDetailById(int employeeId, String value, String fieldName) throws CustomException {
        try {
            PreparedStatement preparedStatement;
            Date date = new Date(0);
            String query = "update employee set " + fieldName + " = ?, modified_date = current_timestamp where id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }

    }
  
    public Employee retriveEmployeeById(int employeeId) throws CustomException {
        try {
            String query = "select * from employee where id = '" + employeeId + "'";
            Employee employee = new Employee(); 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("id"));
                employee.setBatch(resultSet.getInt("batch"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setSubject(resultSet.getString("subject"));
                employee.setGender(resultSet.getString("gender"));
                employee.setDateOfBirth(resultSet.getDate("dob").toLocalDate());
                employee.setDateOfJoining(resultSet.getDate("joining_date").toLocalDate());
                employee.setEmailId(resultSet.getString("email"));
                employee.setMobileNumber(resultSet.getLong("mobile_no"));
                employee.setCreateDate(resultSet.getDate("created_date").toLocalDate());
                employee.setUpdateDate(resultSet.getDate("modified_date").toLocalDate()); 
                                       
            }
         return employee;
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }

    }
   
    public int getLastInsertId(PreparedStatement preparedStatement) throws CustomException {
        int employeeId = 0;
        try {
            String sqlquery = "select last_insert_id()";        
            preparedStatement = connection.prepareStatement(sqlquery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 employeeId = resultSet.getInt("last_insert_id()");
                return employeeId;
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        } 
     
        return employeeId;
    }
}





















