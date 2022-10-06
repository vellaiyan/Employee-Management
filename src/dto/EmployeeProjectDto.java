/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.FetchType; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The {@code EmployeeProjectDto} class represents the all  fields to be accessable for front-end.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding relievedOd.
 */
public class EmployeeProjectDto {
    private int projectId;
    private int employeeId;
    private LocalDate assignedOn;
    private LocalDate completedOn;
    private LocalDate relievedOn;
    private String status;
 
    public EmployeeProjectDto() {
    
    }

    public EmployeeProjectDto(int projectId, int employeeId, LocalDate assignedOn, LocalDate completedOn, LocalDate relievedOn, String status) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.assignedOn = assignedOn;
        this.completedOn = completedOn;
        this.relievedOn = relievedOn;
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }
   
    public int getEmployeeId() {
        return employeeId;
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

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAssignedOn(LocalDate assignedOn) {
        this.assignedOn = assignedOn;
    }

    public void setCompletedOn(LocalDate completedOn) {
        this.completedOn = completedOn;
    }

    public void setRelievedOn(LocalDate relievedOn) {
        this.relievedOn = relievedOn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String output = String.format("%17s %17s %17s %17s %17s %17s\n", projectId, employeeId, assignedOn, completedOn, relievedOn, status);
        
        return output;
    }
}    