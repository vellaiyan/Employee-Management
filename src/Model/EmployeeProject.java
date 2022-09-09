/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType; 
import javax.persistence.*;

@Entity
@Table(name = "employee_projects")
public class EmployeeProject {

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected int id;
 
    @Column(name = "project_name")
    protected String projectName;

    @Column(name = "assigned_on")
    protected LocalDate assignedOn;

    @Column(name = "completed_on")
    protected LocalDate completedOn;

    @Column(name = "relieved_on")
    protected LocalDate relievedOn;
  
    @Column(name = "status")
    protected String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public EmployeeProject() {
    
    }

    public EmployeeProject(String projectName,  LocalDate assignedOn, LocalDate completedOn,
            LocalDate relievedOn, String status) {
        this.projectName = projectName;
        this.assignedOn = assignedOn;
        this.completedOn = completedOn;
        this.relievedOn = relievedOn;
        this.status = status;    
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
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

    public void setAssignedOn(LocalDate assignedOn) {
        this.assignedOn = assignedOn;
    }

    public void setCompletedOn(LocalDate completedOn) {
        this.completedOn = completedOn;
    }
  
    public void setStatus(String status) {
        this.status = status;
    }

    public void setRelievedOn(LocalDate relievedOn) {
        this.relievedOn = relievedOn;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}