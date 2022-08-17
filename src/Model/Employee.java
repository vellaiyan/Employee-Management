/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.model;

/**
 * The {@code Employee} class represents the all common characters 
 * of trainer as well trainees
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding EmployeeId
 */  
public class Employee {    
    protected int age;
    protected int pinCode;
    protected String city;
    protected String taluk;
    protected String state;
    protected String gender;
    protected String emailId;
    protected String lastName;
    protected String district;
    protected int employeeId;
    protected String firstName;
    protected String fatherName;
    protected String motherName; 
    protected String doorNumber; 
    protected long mobileNumber; 
    protected String dateOfBirth; 

    public int getAge() {
        return age;
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
}






