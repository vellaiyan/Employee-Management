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
import com.ideas2it.exception.customException;
 
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The {@code SignInAndLogInController} class helps trainee and trainee to
 * signin or login to their account. 
 * This class also provides modification support aswell. 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Showing details in table format.
 */

public class SignInAndLogInController { 
    private int employeeId = 130;
    private String batch = "";
    private String firstName;
    private String lastName;
    private String subject;
    private String experience;
    private String gender;
    private String dateOfBirth;
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
 
    /**
     * {@code signIn} implemented to sign-in for both trianee and trainer.
     *
     * @param user
     *          Decides which user is going to sign-in (trainer/trainee).
     * 
     * @since 1.0
     *
     */
    public void signIn(String userType) throws customException{
        try{
            firstName = getInput("First Name", ValidationUtil.NAME_PATTERN);
            lastName = getInput("Last Name", ValidationUtil.NAME_PATTERN);
            subject = getInput("Subject", ValidationUtil.NAME_PATTERN);
            experience = getInput("Experience", ValidationUtil.EMPLOYEE_ID_PATTERN);        
            dateOfBirth = getDateOfBirth();
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

        } catch(customException e) {
            throw new customException("invalid input");
        }
        employeeId++;
        int pin = Integer.valueOf(pinCode); 
        long mobile = Long.valueOf(mobileNumber); 
        age = DateUtil.age;
        int batchNo = Integer.valueOf(batch);
        byte trainingBatch = Byte.valueOf(batch);
        EmployeeDto employeeDto = new EmployeeDto(employeeId, batchNo, firstName, lastName, subject, experience, gender, dateOfBirth, age, emailId, mobile,
                doorNumber, fatherName, motherName, city, taluk, district, pin, state);        
        if (employeeService.addEmployee(employeeDto)) {
            System.out.println("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");                
         
        }
    }

    /**
     * {@code getDateOfBirth} get date of birth from employee and 
     * validate it under some checking condition.
     *
     *
     * @return dateOfBirth of employee.
     * 
     * @since 1.0
     *
     */
    public String getDateOfBirth() throws customException{
        boolean isValidDate = true;
        String dateOfBirth = "";
        int remainingTimes = 0;
        //Asking input from user five times if the given input is invalid.
        for (int checkingLoop = 0; checkingLoop < 5; checkingLoop++) {
            System.out.println("Enter your date of birth(dd-MM-yyyy) : ");
            dateOfBirth = scanner.next();
            String date = DateUtil.dateOfBirthValidation(dateOfBirth);
            //Check the user input is valid or not.
            if (date.equals(dateOfBirth)) {
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
                throw new customException("Invalid Input");                
            }
        }
        return dateOfBirth;
    }   

    /**
     * {@code getInput} common method to get input from the employee.
     *
     * @param inputType
     *          type of input.
     * @param regex
     *          pattern to check the given input.
     *
     * @return user input if it is valid.
     * 
     * @since 1.0
     *
     */   
    private String getInput(String inputType, String regex) throws customException{
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
                    throw new customException("Invalid");
                }
        }
        return initialUserInput;
    }    
}