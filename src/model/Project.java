/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.model;

import java.time.LocalDate;

/**
 * The {@code Project} class represents the all  fields related to project.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */  

public class Project {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private String clientName;
    private String companyName;
    private LocalDate startingDate;
    private LocalDate estimatedEndingDate;
    private String deleteStatus;
 
    public Project() {

    }

    public Project (int projectId) {
        this.projectId = projectId;
    }

    public Project(int projectId, String projectName, String projectDescription, String clientName, String companyName, LocalDate startingDate,
            LocalDate estimatedEndingDate, String deleteStatus) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.clientName = clientName;
        this.companyName = companyName;
        this.startingDate = startingDate;
        this.estimatedEndingDate = estimatedEndingDate;  
        this.deleteStatus = deleteStatus;  
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

    public LocalDate getEstimatedEndingDate() {
        return estimatedEndingDate;
    }

    public String setDeleteStatus() {
        return deleteStatus;
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

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

}
