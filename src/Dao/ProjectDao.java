/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
 
package com.ideas2it.dao;

import com.ideas2it.model.Project;
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

public class ProjectDao extends BaseDao {  
    private Connection connection = databaseConnection();

    public int insertProject(Project project) throws CustomException {
        try {
            Date date = new Date(0);
            String query = "insert into project(project_name, project_description, client_name, company_name, starting_date, ending_date, status)"
                + "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectDescription());
            preparedStatement.setString(3, project.getClientName());
            preparedStatement.setString(4, project.getCompanyName());
            preparedStatement.setDate(5, date.valueOf(project.getStartingDate()));
            preparedStatement.setDate(6, date.valueOf(project.getEstimatedEndingDate()));
            preparedStatement.setString(7, "active");
            preparedStatement.execute();
            return getLastInsertId(preparedStatement);
        } catch(SQLException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Project> getAllProjectIds() throws CustomException {
        List<Project> projects = new ArrayList<Project>();
        try {
            String sqlquery = "select id from project where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1, "active");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Project project = new Project();
                project.setProjectId(resultSet.getInt("id"));
                projects.add(project);
            } 
        } catch(SQLException e) {
            throw new CustomException(e.getMessage());
        } 
        return projects;
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