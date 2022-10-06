/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
 
package com.ideas2it.dao;

import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Project;

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

/**
 * The {@code ProjectDao} class implemented to insert, retrive, update, delete all projects.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 * @jls    1.1 Retrive project by projectId.
 */
public class ProjectDao {  
    private Connection connection = databaseConnection();

    /**
     * {@code insertProject} to insert the new project.
     *
     * @param project
     *       project object to be insert.
     *
     * @throws CustomException.
     *
     * @return projectId.
     *
     * @since 1.0
     * 
     */ 
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
        } catch(SQLException exception) {
            throw new CustomException("Error occured while inserting project", exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * {@code retrieveProjects} to retrieve all the projects.
     *
     * @throws CustomException.
     *
     * @return projects.
     *
     * @since 1.0
     * 
     */
    public List<Project> retrieveProjects() throws CustomException {
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
        } catch(SQLException exception) {
            throw new CustomException("Error occured while retrieve projects", exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return projects;
    }

    /**
     * {@code getLastInsertId} to get last insertion id.
     *
     * @throws CustomException.
     *
     * @return project id.
     *
     * @since 1.0
     * 
     */
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
        } catch (SQLException exception) {
            throw new CustomException("Error occured while getting last insertion", exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }  
        return employeeId;
    }
}