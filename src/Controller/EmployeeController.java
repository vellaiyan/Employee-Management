/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.controller;

import com.ideas2it.controller.SignInAndLogInController;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.utilitis.DateUtil;
import com.ideas2it.utilitis.Constants;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TimeZone;

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
    private ProjectService projectService = new ProjectService();
    private SignInAndLogInController signInController = new SignInAndLogInController();
    private static Logger logger = Logger.getLogger(EmployeeController.class);

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();  
        BasicConfigurator.configure();
        do {
            try {
                employeeController.showOptions();
            } catch (InputMismatchException inputMismatchException) {
                logger.error("\nPlease choose valid option");
            }
        } while (isFlow);
    }

    public void showOptions() {
        int userOption;
        System.out.println("\nPlease choose the option below\n");
        Scanner scanner = new Scanner(System.in);
        while (isFlow) {
            System.out.println("1. Trainee SignIn. \n2. Trainer SignIn."
                + "\n3. Projct Manager SignIn .\n4. HumanResource SignIn.  \n5. Add Or Update Or delete projects.  \n6. Assigning projects."
                + "\n7. Trianer Options\n8. Add roles into database. \n9. getProject By project Id. \n10. Exit");
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
                    addOrUpdateProject(Constants.PROJECT_MANAGER);
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
                    } catch (CustomException exception) {
                        System.out.println(exception);
                    }
                    break;
                
                case 8:
                    try {
                        if (employeeService.addRoles()) {
                            logger.info("Roles added successfully\n");
                        } else {
                            logger.error("Failed\n");
                        } 
                    } catch (CustomException custmoException) {
                    }
                    break;
        
                case 9:
                    try {
                        boolean isValidProjectId = true;
                        while (isValidProjectId) {
                            logger.info("Enter the project id you want to get");
                            int projectId = scanner.nextInt();
                            if (projectService.checkIsProjectAvailableById(projectId)) {
                                isValidProjectId = false;
                                System.out.println(projectService.getProjectById(projectId));
                            }
                        } 
                    } catch (CustomException exception) {
                        logger.error(exception);
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
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }

    public void addOrUpdateProject(String userType) {
        try {
            logger.info("Choose the option below.\n 1. Add project. \n2. Update Project. \n3. delete project.");
            Scanner scanner = new Scanner(System.in);
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    signInController.addOrUpdateProject(userType, "add", 0);
                    break;
                case 2:
                    boolean isValidProjectId = true;
                    while (isValidProjectId) {
                        logger.info("Enter the project id you want to update");
                        int projectId = scanner.nextInt();
                        if (projectService.checkIsProjectAvailableById(projectId)) {
                            isValidProjectId = false;
                            signInController.addOrUpdateProject(userType, "update", projectId);
                        } 
                    }
                    break;
                case 3:
                    boolean isValidId = true;
                    while (isValidId) {
                        logger.info("Enter the project id you want to delete");
                        int projectId = scanner.nextInt();
                        if (projectService.checkIsProjectAvailableById(projectId)) {
                            isValidProjectId = false;
                            projectService.deleteProject(projectId);
                        }
                    }    
                    
            }
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }
}