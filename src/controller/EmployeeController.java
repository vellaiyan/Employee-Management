/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.controller;

import com.ideas2it.controller.SignInAndLogInController;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.service.RoleService;
import com.ideas2it.utils.Constants;
import com.ideas2it.utils.DateUtil;

import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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
    RoleService roleService = new RoleService();
    ProjectService projectService = new ProjectService();
    private static boolean isFlow = true;
    SignInAndLogInController signInController = new SignInAndLogInController();    
    public static Logger logger = Logger.getLogger(EmployeeController.class);
    
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

    /**
     * {@code showOptions } to show different options to employee.
     *
     * @since 1.0
     *
     */
    private void showOptions() {
        int userOption;
        logger.info("\nPlease choose the option below\n");
        Scanner scanner = new Scanner(System.in);    
        while (isFlow) {
            logger.info("1. Trainee SignIn. \n2. Trainer SignIn."
                + "\n3. Projct Manager SignIn .\n4. HumanResource SignIn.  \n5. Add Or Update Or delete projects.  \n6. Assigning projects."
                + "\n7. Trianer Options\n8. Add roles into database. \n9. getProject By project Id. \n10. Get all assigned projects."
                + "\n11. Get assigned employees of particular project.");
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
                    addOrUpdateProjectByUserRole(Constants.PROJECT_MANAGER);
                    break;
                
                case 6:
                    signInController.assignEmployees();
                    break;

                case 7:
                    try {
                        signInController.traineeOperations();
                    } catch (CustomException exception) {
                        logger.error(exception);
                    }
                    break;
                
                case 8: 
                    addDefaultRoles();
                    break;
        
                case 9:
                    displayProjectDetails();
                    break;

                case 10:
                    displayAllAssignedProjects();
                    break;
                 
                case 11:
                    displayAssignedEmployeesOfProject(); 
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
    private void userSignIn(String userType) {
        try {
            signInController.signIn(userType,"add", 0);
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }

    /**
     * {@code addOrUpdateProjectByUserRole} is helps the project manager to add or update project details.
     *
     * @param userType
     *        Only project manager get a access to perform project related CRUD operations.
     *
     * @since 1.0
     * 
     */
    private void addOrUpdateProjectByUserRole(String userType) {
        try {
            logger.info("Choose the option below.\n 1. Add project. \n2. Update Project. \n3. delete project.");
            Scanner scanner = new Scanner(System.in);
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    signInController.addAndUpdateProject(userType, "add", 0);
                    break;

                case 2:
                    validateAndUpdateProject();
                    break;

                case 3:
                    validateAndDeleteProject();
                    break;                       
            }
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }
 
    /**
     * {@code displayProjectDetails} is implemented to display the project by validating project id which 
     * was given by user as a input.
     *
     * @since 1.0
     * 
     */
    private void displayProjectDetails() {
        try {
            boolean isValidProjectId = true;
            Scanner scanner = new Scanner(System.in);
            while (isValidProjectId) {
                logger.info("Enter the project id you want to get");
                int projectId = scanner.nextInt();
                if (projectService.checkIsProjectAvailableById(projectId)) {
                    isValidProjectId = false;
                    logger.info(projectService.getProjectById(projectId));
                }
            }
        } catch (CustomException exception) {
            logger.error(exception);
        }
    } 

    /**
     * {@code displayAllAssignedProjects} is implemented to display all the assigned project to all the employees.
     *
     * @since 1.0
     * 
     */
    private void displayAllAssignedProjects() {
        try {
            List<EmployeeProjectDto> employeeProjectDtos = projectService.getAllAssignedProjects();
            for(EmployeeProjectDto employeeProjectDto: employeeProjectDtos) {
                logger.info(employeeProjectDto);   
            }
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }

    /**
     * {@code getProjectId} is common method for asking the project id from the user for validation.
     *
     * @param userNeed
     *        For what operation user going to perform related to project.
     * 
     * @return projectId.
     *
     * @since 1.0
     * 
     */
    private int getProjectId(String userNeed) {
        Scanner scanner = new Scanner(System.in);
        logger.info(userNeed);
        int projectId = scanner.nextInt();
        
        return projectId;
    }

    /**
     * {@code displayAssignedEmployeesOfProject} is displays all the employees whos are assigned in single project.
     *
     * @since 1.0
     * 
     */
    private void displayAssignedEmployeesOfProject() {
        try {
            boolean isValidProjectId = true;
            while (isValidProjectId) {
                int projectId = getProjectId("Enter the project Id want to see assigned employees");
                if (projectService.checkIsProjectAvailableById(projectId)) {
                    isValidProjectId = false;
                    signInController.displayAllEmployees(projectService.getAssignedEmployeesForSingleProject(projectId));
                }
            } 
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }  

    /**
     * {@code ValidateAndupdateProject} implemented to validate and update the project details.
     *
     *
     * @since 1.0
     * 
     */
    private void validateAndUpdateProject() {
        try {    
            boolean isValidProjectId = true;
            while (isValidProjectId) {
                int projectId = getProjectId("Enter the Project id you want to update");
                if (projectService.checkIsProjectAvailableById(projectId)) {
                    isValidProjectId = false;
                    signInController.addAndUpdateProject(Constants.PROJECT_MANAGER, "update", projectId);
                } 
            } 
        } catch (CustomException exception) {
            logger.error(exception);
        }   
    } 

    /**
     * {@code deleteProject} is implemented to validate and delete the project.
     *
     * @since 1.0
     * 
     */
    private void validateAndDeleteProject() {
        try {
            boolean isValidId = true;
            while (isValidId) {
                int projectId = getProjectId("Enter the projectId you want to delete");
                if (projectService.checkIsProjectAvailableById(projectId)) {
                    projectService.deleteProject(projectId);
                    isValidId = false;
                }
            }
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }

    /**
     * {@code addDefaultRoles} is helps to add default roles in the database.
     *
     * @since 1.0
     * 
     */
    private void addDefaultRoles() {
        try {
            if (roleService.addRoles()) {
                logger.info("Roles added successfully\n");
            } else {
                logger.error("Failed\n");
            } 
        } catch (CustomException exception) {
            logger.error(exception);
        }
    }
}