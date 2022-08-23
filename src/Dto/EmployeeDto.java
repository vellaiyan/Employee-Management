/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dto;

/**
 * The {@code Employee} class represents the all common characters 
 * of trainer as well trainees
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding EmployeeId
 */  
public class EmployeeDto { 
    protected int employeeId;
    protected int batch;
    protected String firstName;
    protected String lastName;
    protected String subject;
    protected String experience;
    protected String gender;
    protected String dateOfBirth;
    protected int age;
    protected String emailId;
    protected long mobileNumber; 
    protected String doorNumber; 
    protected String fatherName;
    protected String motherName; 
    protected String city;
    protected String taluk;
    protected String district;
    protected int pinCode;
    protected String state;

    public EmployeeDto(int employeeId, int batch , String firstName, String lastName, String subject, String experience, String gender,
            String dateOfBirth, int age, String emailId, long mobileNumber, String doorNumber, String fatherName, String motherName, String city,
            String taluk, String district, int pinCode, String state) {
        this.employeeId = employeeId;
        this.batch = batch;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.experience = experience;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.doorNumber = doorNumber;
        this.fatherName = fatherName; 
        this.motherName = motherName;
        this.city = city;
        this.employeeId = employeeId;
        this.pinCode = pinCode;
        this.taluk = taluk;
        this.district = district;
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public String getExperience() {
	return experience;
    }
   
    public int getBatch() {
        return batch;
    }

    public String getSubject() {
        return subject;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
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

    public String getDoorNumber() {
        return doorNumber;
    }

    public String getCity() {
        return city;
    }

    public String getTaluk() {
        return taluk;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public int getPinCode() {
        return pinCode;
    }

    public int getEmployeeId() {
        return employeeId;
    }

   public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }

    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    } 

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName; 
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName; 
    }

    public void setEmailId(String emailId) { 
        this.emailId = emailId; 
    }

    public void setCity(String city) {
        this.city = city;	
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
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

    public void setId(short employeeId) {
        this.employeeId = employeeId;
    }
   
    public void setAge(int age) {
        this.age = age;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
  
    public void setSubject(String subject) {
        this.subject = subject;

    } 

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override  
    public String toString() {
        String output = String.format("%5s %11s %10s %12s %12s %12s %12s %15s %8s %20s %16s %10s %7S %7s %9s %5s", employeeId, batch, 
                firstName, lastName, subject, experience, gender, dateOfBirth, age, emailId, mobileNumber, doorNumber, city, taluk, pinCode, "|");
        return  output;              
    }
}






