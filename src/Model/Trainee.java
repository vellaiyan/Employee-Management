/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.model;

public class Trainee extends Employee {
    protected int batch;
    protected String id;

    public Trainee(String firstName, String lastName, int employeeId, int batch, String date, String fatherName, String motherName, String gender,
            String emailId, long mobileNo, String doorNo, String city, String taluk, String state, String district,
	    int pinCode, int age) {
        this.dateOfBirth = date;
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.emailId = emailId;
        this.mobileNumber = mobileNo;
        this.doorNumber = doorNo;
        this.city = city;
        this.taluk = taluk;
        this.state = state;
        this.district = district;
        this.pinCode = pinCode;
        this.batch = batch;
        this.employeeId = employeeId;
        this.age = age;
    }

    public int getBatch() {
	return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
  
    @Override  
    public String toString() {
        String output = String.format("%5s %11s %10s %12s %15s %8s %20s %16s %10s %7S %7s %9s %5s", employeeId, batch, 
                firstName, gender, dateOfBirth, age, emailId, mobileNumber, doorNumber, city, taluk, pinCode, "|");
        return  output;              
    }
}