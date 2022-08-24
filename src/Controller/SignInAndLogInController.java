/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.controller;

import com.ideas2it.service.EmployeeService;
import com.ideas2it.utilitis.ValidationUtil;
import com.ideas2it.utilitis.DateUtil;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
 
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SignInAndLogInController { 
    private int employeeId = 130;
    private String batch = "";
    private String firstName;
    private String lastName;
    private String subject;
    private String experience;
    private String gender;
    private String dateOfBirth;
    private String dateOfJoining;
    private int age;
    private String emailId;
    private String mobileNumber;
    private String doorNumber;
    private String city;
    private String taluk;
    private String pinCode;
    private String state;
    private String district;
    private String fatherName;
    private String motherName;
    private boolean isContinue = true;
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
 
    public void signIn(String userRole) throws CustomException{
        try{
            firstName = getInput("First Name", ValidationUtil.NAME_PATTERN);
            lastName = getInput("Last Name", ValidationUtil.NAME_PATTERN);
            subject = getInput("Subject", ValidationUtil.NAME_PATTERN);
            experience = getInput("Experience", ValidationUtil.EMPLOYEE_ID_PATTERN);        
            dateOfBirth =  getDateOfBirthAndJoining("Enter your date of birth (dd/MM/yyyy)", "dob");
            dateOfJoining = getDateOfBirthAndJoining("Enter your date of joining (dd/MM/yyyy)", "joining");
            batch = getInput("batch Number", ValidationUtil.EMPLOYEE_ID_PATTERN);
            fatherName = getInput("Father Name", ValidationUtil.NAME_PATTERN);
            motherName = getInput("Mother Name", ValidationUtil.NAME_PATTERN);
            gender = getInput("Gender", ValidationUtil.GENDER_PATTERN);
            emailId = getInput("Email-Id", ValidationUtil.EMAIL_PATTERN);
            mobileNumber = getInput("Mobile Number", ValidationUtil.PHONE_PATTERN);
            doorNumber = getInput("Door Number", ValidationUtil.DOOR_PATTERN);
            city = getInput("City Name", ValidationUtil.NAME_PATTERN);
            taluk = getInput("Taluk", ValidationUtil.NAME_PATTERN);
            state = getInput("State", ValidationUtil.NAME_PATTERN);
            district = getInput("District", ValidationUtil.NAME_PATTERN);
            pinCode = getInput("PinCode", ValidationUtil.PIN_PATTERN);
        } catch(CustomException e) {
            throw new CustomException("invalid input");
        }
        employeeId++;
        int pin = Integer.valueOf(pinCode); 
        long mobile = Long.valueOf(mobileNumber); 
        age = DateUtil.age;
        int batchNo = Integer.valueOf(batch);
        byte trainingBatch = Byte.valueOf(batch);
        EmployeeDto employeeDto = new EmployeeDto(employeeId, batchNo, firstName, lastName, subject, experience, gender, dateOfBirth, dateOfJoining,
                age, emailId, mobile, doorNumber, fatherName, motherName, city, taluk, district, pin, state);        
        if (employeeService.addEmployee(employeeDto, userRole)) {
            System.out.println("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");                
         
        }
    }

    public String  getDateOfBirthAndJoining(String input, String choosenDate) throws CustomException{
        boolean isValidDate = true;
        String dateOfBirthAndJoining = "";
        int remainingTimes = 0;
        //Asking input from user five times if the given input is invalid.
        for (int checkingLoop = 0; checkingLoop < 5; checkingLoop++) {
            System.out.println(input);
            dateOfBirthAndJoining = scanner.next();
            String date = DateUtil.dateOfBirthValidation(dateOfBirthAndJoining, choosenDate);
            //Check the user input is valid or not.
            if (date.equals(dateOfBirthAndJoining)) {
                isValidDate = false;
                checkingLoop = 4;
                dateOfBirth = date;
            } else if (date.equals("max")) {
                System.out.println("Your date of birth exceeds current year");
                remainingTimes++;
            } else if (date.equals("low")) { 
                System.out.println("Given date of birth is not valid");
                remainingTimes++;
            } else if (date.equals("min")) {
                System.out.println("Your age is too low");
                remainingTimes++;
            } else if (date.equals("invalid")) {
                System.out.println("Invalid dateOfBirth");
                remainingTimes++;
            } if (remainingTimes == 5) {
                throw new CustomException("Invalid Input");                
            }
        }
        return dateOfBirthAndJoining;
    }   
  
    private String getInput(String inputType, String regex) throws CustomException{
        String initialUserInput = "";
        int remainingTimes = 0;
        //Asking input from user five times if the given input is invalid.
        for(int numberOfTimes = 0; numberOfTimes < 5; numberOfTimes++) {
                System.out.println("Enter Your " + inputType + " : ");
                String userInput = scanner.next();
                boolean isvalid = ValidationUtil.inputCheckingByRegex(regex, userInput);
                //Check the user input is valid or not.
                if (isvalid) {
                    initialUserInput = userInput;
                    numberOfTimes = 4;               
                } else if (!isvalid) {
                    System.out.println("Invalid " + inputType + "("+(5-(numberOfTimes+1)) + " more times)");
                    remainingTimes++;                                 
                } if (remainingTimes == 5){
                    throw new CustomException("Invalid");
                }
        }
        return initialUserInput;
    }    
}