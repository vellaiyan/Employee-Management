   /*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.model;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 * The {@code Employee} class represents the all  fields  of employees.
 * 
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding EmployeeId
 */  

public class Employee { 
    protected int employeeId;
    protected int batch;
    protected String firstName;
    protected String subject;
    protected String gender;
    protected LocalDate dateOfBirth;
    protected LocalDate dateOfJoining;
    protected String emailId;
    protected long mobileNumber;  
    protected LocalDate createDate;
    protected LocalDate modifiedDate;
    protected String status;
    protected String role;
    protected List<Role> roles = new ArrayList<Role>();

    public Employee() {

    }

    public Employee(int employeeId, int batch , String firstName, String subject, String gender,
            LocalDate dateOfBirth, LocalDate dateOfJoining, String emailId, long mobileNumber,
            LocalDate createDate, LocalDate modifiedDate, String role, String status) {
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
        this.role = role;
        this.status = status;
    }

    public Employee(int batch , String firstName, String subject, String gender,
            LocalDate dateOfBirth, LocalDate dateOfJoining, String emailId, long mobileNumber,
            LocalDate createDate, LocalDate modifiedDate, String role, String status) {
        
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
        this.role = role;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public String getRole() {
        return role;
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
   
    public LocalDate getCreateDate() {
        return createDate;
    }
  
    public LocalDate getModifiedDate() {
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

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
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

    public void setRole(String role) {
        this.role = role;
    }

}



