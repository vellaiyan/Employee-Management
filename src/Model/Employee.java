   /*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.model;

import java.time.LocalDate;

public class Employee { 
    protected int employeeId;
    protected int batch;
    protected String firstName;
    protected String subject;
    protected String gender;
    protected LocalDate dateOfBirth;
    protected LocalDate dateOfJoining;
    protected LocalDate createDate;
    protected LocalDate updateDate;
    protected String timeZone;
    protected String emailId;
    protected long mobileNumber;  

    public Employee() {

    }

    public Employee(int employeeId, int batch , String firstName, String subject, String gender,
            LocalDate dateOfBirth, LocalDate dateOfJoining, String emailId, long mobileNumber, LocalDate createDate, LocalDate updateDate) {
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
        this.updateDate = updateDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getTimeZone() {
        return timeZone;
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
  
    public LocalDate getUpdateDate() {
        return updateDate;
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

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
 
    public void setBatch(int batch) {
        this.batch = batch;
    }

    public void getTimeZone(String timeZone) {
        this.timeZone = timeZone; 
    }
}



