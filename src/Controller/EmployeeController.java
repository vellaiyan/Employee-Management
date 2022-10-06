/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.controller;

import com.ideas2it.controller.SignInAndLogInController;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.utilitis.Constants;
import com.ideas2it.utilitis.DateUtil;

import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * The {@code EmployeeController} class is to show and control the employee's options.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 * @jls    1.1  Additional option to trainees and trainers.
 */
public class EmployeeController {
    private static boolean isFlow = true;
    private EmployeeService employeeService = new EmployeeService();
    private SignInAndLogInController signInController = new SignInAndLogInController();

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();      
        do {
            try {
                TimeZone timeZone = TimeZone.getDefault();
                System.out.println(timeZone);
                employeeController.showOptions();
            } catch (InputMismatchException e) {
                System.out.println("********************************** INVALID OPTION ************************************");
                System.out.println("\nPlease choose valid option");
            }
        } while (isFlow);
    }

    /**
     * {@code showOptions } to show different options to employee.
     *
     * @since 1.0
     *
     */
    public void showOptions() {
        int userOption;
        System.out.println("\nPlease choose the option below\n");
        Scanner scanner = new Scanner(System.in);
        while (isFlow) {
            System.out.println("1. Trainee SignIn. \n2. Trainer SignIn."
                + "\n3. Projct Manager SignIn .\n4. HumanResource SignIn.  \n5. Add projects. \n6. Assigning projects."
                + "\n7. Trianer Options\n8. Exit");
            userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    userSignIn(Constants.TRAINEE);
                    break;

                case 2:
                    userSignIn(Constants.TRAINER);
                    break;

                case 3:
                    userSignIn(Constants.PROJECT_MANAGER);
                    break;

                case 4:
                    userSignIn(Constants.HUMAN_RESOURCE);
                    break;
 
                case 5:
                    addProject(Constants.PROJECT_MANAGER);
                    break;
                
                case 6:
                    try {
                        signInController.assignEmployees();
                    } catch (CustomException e) {
                        
                    }
                    break;

                case 7:
                    try {
                        System.out.print("Enter the user name : ");
                        String userName = scanner.next();
                        System.out.print("Enter the password : ");
                        String password = scanner.next();
                        signInController.trainerOperations(userName, password);
                    } catch(CustomException e) {
                    
                    }
                    break;

                default:
                    isFlow = false;
            }
        }       
    }
 

    /**
     * {@code userSignIn} common method for all employee to sign-in.
     *
     * @param userType
     *       Which user (Trainee/Trainer/Project manager/Human Resource) is going to sign-in.
     *
     * @since 1.0
     * 
     */
    public void userSignIn(String userType) {
        try {
            signInController.signIn(userType,"add", 0);
        } catch (CustomException e) {
            System.out.println("\n"+ e.getMessage() + "\nSorry you gave wrong information please try again !!! \n");
        }
    }

    /**
     * {@code addProject} is helps the project manager to add project details.
     *
     * @param userType
     *        Only project manager get a access to perform project related CRUD operations.
     *
     * @since 1.0
     * 
     */
    public void addProject(String userType) {
        try {
            signInController.addProject(userType, "add");
        } catch(CustomException e) {
            System.out.println(e);
            System.out.println("assigning is not working properly");
        }
    }
}