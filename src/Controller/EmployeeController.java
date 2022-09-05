/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.controller;

import com.ideas2it.controller.SignInAndLogInController;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.utilitis.DateUtil;
import com.ideas2it.exception.CustomException;
import com.ideas2it.utilitis.Constants;

import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TimeZone;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The {@code EmployeeController} class is to show and control the employee options.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1  Additional option to trainees to trainers.
 */

public class EmployeeController {
    private static boolean isFlow = true;
    private EmployeeService employeeService = new EmployeeService();
    private SignInAndLogInController signInController = new SignInAndLogInController();
    private static SessionFactory factory;

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();      
        do {
            try {
                employeeController.showOptions();
            } catch (InputMismatchException e) {
                System.out.println("********************************** INVALID OPTION ************************************");
                System.out.println("\nPlease choose valid option");
            }
        } while (isFlow);
    }

    public void showOptions() {
        int userOption;
        System.out.println("\nPlease choose the option below\n");
        Scanner scanner = new Scanner(System.in);
        while (isFlow) {
            System.out.println("1. Trainee SignIn. \n2. Trainer SignIn."
                + "\n3. Projct Manager SignIn .\n4. HumanResource SignIn.  \n5. Add projects. \n6. Assigning projects."
                + "\n7. Trianer Options\n8. Add roles into database. \n9. Exit.");
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
                
                case 8:
                    if(employeeService.addRoles()) {
                        System.out.println("Roles added successfully\n");
                    } else {
                        System.out.println("Failed\n");
                    }
                    break;

                default:
                    isFlow = false;
            }
        }       
    }
 
    public void userSignIn(String userType) {
        try {
            signInController.signIn(userType,"add", 0);
        } catch (CustomException e) {
            System.out.println("\n"+ e.getMessage() + "\nSorry you gave wrong information please try again !!! \n");
        }
    }

    public void addProject(String userType) {
        try {
            signInController.addProject(userType, "add");
        } catch(CustomException e) {
            System.out.println(e);
            System.out.println("assigning is not working properly");
        }
    }
}