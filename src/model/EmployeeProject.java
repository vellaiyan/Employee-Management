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
import javax.persistence.FetchType; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "employee_projects")
public class EmployeeProject {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Transient 
    private int employeeId;
  
    @Transient
    private int projectId;

    @Column(name = "assigned_on")
    private LocalDate assignedOn;

    @Column(name = "completed_on")
    private LocalDate completedOn;

    @Column(name = "relieved_on")
    private LocalDate relievedOn;
  
    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public EmployeeProject() {
    
    }

    public EmployeeProject(LocalDate assignedOn, LocalDate completedOn,
            LocalDate relievedOn, String status) {
        this.assignedOn = assignedOn;
        this.completedOn = completedOn;
        this.relievedOn = relievedOn;
        this.status = status;    
    }

    public EmployeeProject(int employeeId, int projectId, LocalDate assignedOn, LocalDate completedOn,
            LocalDate relievedOn, String status) {
        this.employeeId = employeeId;
        this.projectId = projectId;
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

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}