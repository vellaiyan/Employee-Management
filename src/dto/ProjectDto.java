/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dto;

import com.ideas2it.model.EmployeeProject;

import java.time.LocalDate;
import java.util.List;

/**
 * The {@code ProjectDto} class represents the all  fields to be accessable for front-end.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 * @jls    1.1 Adding status.
 */ 

public class ProjectDto {
    protected int projectId;
    protected int employeeId;
    protected String projectName;
    protected String projectDescription;
    protected String clientName;
    protected String companyName;
    protected LocalDate startingDate;
    protected LocalDate estimatedEndingDate;
    protected String status;
    protected EmployeeProject employeeProjects;

    public ProjectDto() {
    
    }

    public ProjectDto(int projectId, String projectName, String projectDescription, String clientName, String companyName, LocalDate startingDate,
            LocalDate estimatedEndingDate, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startingDate = startingDate;
        this.estimatedEndingDate = estimatedEndingDate; 
        this.status = status;  
      
    }

    public EmployeeProject getEmployeeProjects() {
        return employeeProjects;
    }

    public int getProjectId() {
        return projectId; 
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getEstimatedEndingDate() {
        return estimatedEndingDate;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    } 

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
 
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setEstimatedEndingDate(LocalDate estimatedEndingDate) {
        this.estimatedEndingDate = estimatedEndingDate;
    }
  
    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeProjects(EmployeeProject employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    @Override
    public String toString() {
        String output = String.format("%15s %15s %15s %15s %15s %15s %15s", projectId, projectDescription, clientName, companyName, startingDate,                         estimatedEndingDate, status);
        return output;
    }
}