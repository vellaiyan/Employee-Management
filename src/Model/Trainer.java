/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.model;

public class Trainer extends Employee {
    protected String experience;
    protected String subject;

    public Trainer(String firstName, String lastName, int id, String subject, String experience, String date, String fatherName,
            String motherName, String gender, String emailId, long mobileNo, String doorNo, String city, String taluk,
            String state, String district, int pinCode, int age) {
        this.firstName = firstName;
	this.lastName = lastName;
        this.dateOfBirth = date;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.gender = gender;
        this.emailId = emailId;
        this.mobileNumber = mobileNo;
        this.doorNumber = doorNo;
        this.city = city;
        this.taluk = taluk;
        this.state = state;
        this.district = district;
        this.pinCode = pinCode;
        this.experience = experience;
        this.subject = subject;
        this.employeeId = id;
        this.age = age;
    }

    public String getExperience() {
	return experience;
    }

    public String getSubject() {
        return subject;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
  
    public void setSubject(String subject) {
        this.subject = subject;

    }
    @Override  
    public String toString() {
        String output = String.format("%5s %12s %12s %10s %10s %14s %8s %18s %15s %12s %7s %8s %11s %4s ", employeeId, firstName, subject, experience,
            gender, dateOfBirth, age, emailId, mobileNumber, doorNumber, city, taluk, pinCode, "|");
        return  output;             
    }
}