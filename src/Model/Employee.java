   /*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.model;

public class Employee { 
    protected int employeeId;
    protected int batch;
    protected String firstName;
    protected String subject;
    protected String gender;
    protected String dateOfBirth;
    protected String dateOfJoining;
    protected String createDate;
    protected String updateDate;
    protected String emailId;
    protected long mobileNumber;  

    public Employee() {

    }

    public Employee(int employeeId, int batch , String firstName, String subject, String gender,
            String dateOfBirth, String dateOfJoining, String emailId, long mobileNumber, String createDate, String updateDate) {
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
 
    public String getDateOfJoining() {
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
   
    public String getCreateDate() {
        return createDate;
    }
  
    public String getUpdateDate() {
        return updateDate;
    }
  
    public String getDateOfBirth() {
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

    public void setDateOfBirth(String date) {
        this.dateOfBirth = date;
    }
  
    public void setSubject(String subject) {
        this.subject = subject;
    } 

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
 
    public void setBatch(int batch) {
        this.batch = batch;
    }
}



