/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.model;

import com.ideas2it.model.Role;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The {@code Employee} class represents the all  fields  of employees.
 * 
 * @author Vellaiyan
 *
 * @since  1.0
 *
 * @jls    1.1 Adding batch.
 */  
@Entity
@Table(name = "employee")
public class Employee {
 
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int employeeId;

    @Column(name = "batch")
    private int batch;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "subject")
    private String subject;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_Of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_number")
    private long mobileNumber;  

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "status")
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles", joinColumns = {@JoinColumn(name = "employee_id") }, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<Role>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeProject> EmployeeProjects ;

    public Employee() {

    }

    public Employee(int employeeId, int batch , String firstName, String subject, String gender,
            LocalDate dateOfBirth, LocalDate dateOfJoining, String emailId, long mobileNumber,
            LocalDateTime createDate, LocalDateTime modifiedDate, String status) {
        this.employeeId = employeeId;
        this.batch = batch;
        this.firstName = firstName;
        this.subject = subject;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public List<EmployeeProject> getEmployeeProjects() {
        return EmployeeProjects;
    }

    public String getSubject() {
        return subject;
    }
  
    public String getStatus() {
        return status;
    }
 
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }
    
    public int getBatch() {
        return batch;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getEmailId() {
         return emailId;
    }


    public String getGender() {
        return gender;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }
   
    public LocalDateTime getCreateDate() {
        return createDate;
    }
  
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
  
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

   public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }

    public void setEmailId(String emailId) { 
        this.emailId = emailId; 
    }


    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDate date) {
        this.dateOfBirth = date;
    }
  
    public void setSubject(String subject) {
        this.subject = subject;
    } 

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
 
    public void setBatch(int batch) {
        this.batch = batch;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setEmployeeProjects(List<EmployeeProject> EmployeeProjects) {
        this.EmployeeProjects = EmployeeProjects;
    }
}