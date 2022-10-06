/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;  
import java.text.SimpleDateFormat; 
  
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

import com.ideas2it.exception.CustomException;
import com.ideas2it.model.EmployeeProject;

/**
 * The {@code } class implemented to insert, retrive, update, delete all employees roles.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */

public class EmployeeProjectDao extends BaseDao {
    Connection connection = databaseConnection();
 
    public boolean assignEmployeeProject(int employeeId, int projectId, LocalDate assignDate, LocalDate completionDate) throws CustomException{
        try {
            Date date = new Date(0);
            String query = "insert into employee_project(employee_id, project_id, assigned_on, completed_on, status)" + " values(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, projectId);
            preparedStatement.setDate(3, date.valueOf(assignDate));
            preparedStatement.setDate(4, date.valueOf(completionDate));
            preparedStatement.setString(5, "active");
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage());
        }
        return true;
    } 

    public List<EmployeeProject> retriveAssignedEmployees(int projectId) {
        List<EmployeeProject> projects = new ArrayList<EmployeeProject>();
        try {

            System.out.println("=======================entered====================");
            System.out.println(projectId);
            String query = "select employee_project.employee_id, employee_project.project_id, employee_project.assigned_on,"
                +"employee_project.completed_on, employee_project.relieved_on, project.project_name from employee_project, project where project.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                EmployeeProject employeeProject = new EmployeeProject();
                System.out.println(resultSet.getInt("employee_id"));
                System.out.println(resultSet.getInt("project_id"));
                System.out.println(resultSet.getString("project_name"));
                System.out.println(resultSet.getDate("assigned_on").toLocalDate());
                System.out.println(resultSet.getDate("completed_on").toLocalDate());
                System.out.println(resultSet.getDate("relieved_on").toLocalDate());
                System.out.println(resultSet.getString("status"));

                employeeProject.setEmployeeId(resultSet.getInt("employee_id"));
                employeeProject.setProjectId(resultSet.getInt("project_id"));
                employeeProject.setProjectName(resultSet.getString("project_name"));
                employeeProject.setAssignedOn(resultSet.getDate("assigned_on").toLocalDate());
                employeeProject.setCompletedOn(resultSet.getDate("completed_on").toLocalDate());
                employeeProject.setRelievedOn(resultSet.getDate("relieved_on").toLocalDate());
                employeeProject.setStatus(resultSet.getString("status"));
                projects.add(employeeProject);
            }
            System.out.println(projects);
            return projects;
        } catch(Exception e) {
    
        }
        return projects;
     
    }
            
}
