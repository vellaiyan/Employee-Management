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
import com.ideas2it.utilitis.Constants;
import com.ideas2it.exception.CustomException;
 
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;   
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class SignInAndLogInController { 
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    public void signIn(String userRole, String operation, int employeeId) throws CustomException{
    EmployeeDto employeeDto;
        try { 
            employeeDto = new EmployeeDto();      
            employeeDto.setEmployeeId(0);
            employeeDto.setFirstName(getInput("First Name", ValidationUtil.NAME_PATTERN));
            employeeDto.setSubject(getInput("Subject", ValidationUtil.NAME_PATTERN));
            employeeDto.setDateOfBirth(LocalDate.parse(getDateOfBirthAndJoining("Enter your date of birth (yyy-MM-dd)", "dob")));
            employeeDto.setDateOfJoining(LocalDate.parse(getDateOfBirthAndJoining("Enter your date of joining (yyyy-MM-dd)", "joining")));
            employeeDto.setBatch(Integer.parseInt((getInput("batch Number", ValidationUtil.EMPLOYEE_ID_PATTERN))));
            employeeDto.setGender(getInput("Gender", ValidationUtil.GENDER_PATTERN));
            employeeDto.setEmailId(getInput("Email-Id", ValidationUtil.EMAIL_PATTERN));
            employeeDto.setMobileNumber(Long.parseLong(getInput("Mobile Number", ValidationUtil.PHONE_PATTERN)));
            employeeDto.setCreateDate(LocalDate.parse("2000-02-28"));
            employeeDto.setUpdateDate(LocalDate.parse("2000-02-28"));
        } catch(CustomException e) {
            throw new CustomException("invalid input");
        }
      
        if (operation.equals("add")) {
            boolean isAdded = employeeService.addEmployee(employeeDto, userRole);
            System.out.println("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");                         
        } else {
            boolean isUpdated = employeeService.updateEmployeeDetails(employeeDto, employeeId);
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
                dateOfBirthAndJoining = date;
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

    public void traineeOperations(String name, String password) {
        Scanner scannerInput = new Scanner(System.in);
        boolean isContinue = true;
        if (name.equals("ideas2it") && password.equals("admin")) {
            System.out.println("1. View all trainee details. \n2. View all trainer details"
                +"\n3. Update employee details. \n4. Delete employee \n5. Update employee particular detail. \n6. View particular employee"
                +"\n7. Exit");
            String userOption = scannerInput.next();
                switch (userOption) {
                    case "1":
                        try {
                            displayEmployees(Constants.TRAINEE);
                        } catch(Exception e) {
                            System.out.println("sorry");
                        }
                        break;
 
                    case "2":
                        try {
                            displayEmployees(Constants.TRAINER);
                        } catch(Exception e) {
                            System.out.println("sorry");
                        }
                        break;

                    case "3":
                         try {
                             updateEmployeeDetails();
                         } catch(Exception e) {
                             System.out.println("sorry");
                         }
                         break;

                    case "4":
                        try {
                            deleteEmployee();
                        } catch(Exception e) {
                            System.out.println("Sorry");
               
                        }
                        break;
                    
                    case "5":
                        try {
                            System.out.println("Enter the user email id you want to update");
                            int employeeId= scanner.nextInt();
                            if(!employeeService.checkEmployeeById(employeeId)) {
                                System.out.println("Invalid email Id");
                            } else {
                                updateEmployeeDetail(employeeId);
                            }
                            
                        } catch (Exception e) {
                            System.out.println("sorry");

                        }
                        break;

                    case "6":
                        try {
                            System.out.println("Enter the trainee Id you want to see");
                            int traineeId = scanner.nextInt();
                            if(!employeeService.checkEmployeeById(traineeId)) {
                                System.out.println("\nInvalid Employe Id\n");   
                            } else {
                                System.out.println(employeeService.getEmployeeById(traineeId));
                            }

                        } catch(Exception e) {
                            System.out.println("sorry");

                        }
                        break;
                    default:
                        isContinue = false;
                }        
        }
    } 

    public void displayEmployees(String userRole) throws CustomException {
        List<EmployeeDto> employees = employeeService.getEmployeesByRole(userRole);
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%17s %8s %8s %15s %8s %15s %5s %15s %8s %15s %20s \n", "Id", "Batch", "First Name", 
            "Subject", "Gender", "Date Of Birth", "Date Of Joining", "Create Date", "Update Date", "email Id", "Mobile Number"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");  
        for(EmployeeDto employeeDto : employees) {
            System.out.println(employeeDto);
        }  
    }

    public void updateEmployeeDetails() throws CustomException {
        System.out.println("Enter the Employee Id you want to update");
        int employeeId = scanner.nextInt();
        if(employeeService.checkEmployeeById(employeeId)) {
            signIn("employee", "update", employeeId);
        } else {
            System.out.println("\n not available \n");

        }       
        
    }
   
    public void deleteEmployee() throws CustomException{
        System.out.println("Enter the employee Id you want to delete");
        int employeeId = scanner.nextInt();
        if(employeeService.checkEmployeeById(employeeId)) {
            boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
            System.out.println("Employee deleted");
        } else {
            System.out.println("Not available\n");

        } 
    }

    public void updateEmployeeDetail(int employeeId) throws CustomException {
        System.out.println("Choose what details you want to change.\n1. Batch.\n2. First Name. \n3. Subject. \n4. Gender."
            + "\n5. Date of birth. \n6. Joining date. \n7. Email Id. \n8. Mobile Number.");
        int employeeChoice = scanner.nextInt();
        switch(employeeChoice) {
            case 1: 
                employeeService.updateEmployeeDetail(employeeId, gettingUpdatedValue("First Name"), "first_name");
                break;
        }
    }

    public String gettingUpdatedValue(String value) {
        System.out.println("Enter your" + value + ":");
        String updatedInput = scanner.next();
        return updatedInput;
    }
}