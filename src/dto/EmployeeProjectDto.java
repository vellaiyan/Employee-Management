/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dto;

import java.time.LocalDate;

public class EmployeeProjectDto {
    private int employeeId;
    private int projectId;
    private String projectName;
    private LocalDate assignedOn;
    private LocalDate completedOn;
    private LocalDate relievedOn;
    private String status;
    
    public EmployeeProjectDto(int employeeId, int projectId, String projectName,  LocalDate assignedOn, LocalDate completedOn,
            LocalDate relievedOn, String status) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.assignedOn = assignedOn;
        this.completedOn = completedOn;
        this.relievedOn = relievedOn;
        this.status = status;    
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
    public LocalDate getAssignedOn() {
        return assignedOn;
    }

    public LocalDate getCompletedOn() {
        return completedOn;
    }

    public LocalDate getRelievedOn() {
        return relievedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setAssignedOn(LocalDate assignedOn) {
        this.assignedOn = assignedOn;
    }

    public void setCompletedOn(LocalDate completedOn) {
        this.completedOn = completedOn;
    }
  
    public void setStatus(String status) {
        this.status = status;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() { 
        String output = String.format("%17s %17s %17s %15s %15s %15s %5s", employeeId, projectId, projectName, assignedOn, completedOn, relievedOn, status);    
        return output;   
    }
}