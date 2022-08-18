/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.controller;
import com.ideas2it.controller.SignInAndLogInController;
import com.ideas2it.model.Trainee;
import com.ideas2it.service.TraineeService;
import com.ideas2it.service.TrainerService;
import com.ideas2it.utilitis.DateUtil;
import com.ideas2it.exception.customException;

import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
/**
 * The {@code EmployeeController} class is to show and control the 
 * employee options.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1  Additional option to add defaulttrainees and trainers
 */

public class EmployeeController {
    private static boolean isFlow = true;
    private  TrainerService trainerService = new TrainerService();
    private  TraineeService traineeService = new TraineeService();
    private SignInAndLogInController signinController = new SignInAndLogInController();
    private static Logger logger = Logger.getLogger(EmployeeController.class);

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();  
        BasicConfigurator.configure();    
        do {
            try {
                employeeController.showOptions();
            } catch (InputMismatchException e) {
                logger.info("********************************** INVALID OPTION ************************************");
                logger.info("\nPlease choose valid option");
            }
        } while (isFlow);
    }

     /**
     * {@code showOptions} to show different options to employee.
     *
     * @param dob
     *          The date of birth need to be validate.
     * 
     * @throws InputMismatchException
     *
     * @since 1.0
     *
     */
    public void showOptions() {
        int userOption;
        logger.info("Please choose the option below");
        Scanner scanner = new Scanner(System.in);
        while (isFlow) {
            logger.info("\n1. Trainee SignIn. \n2. Trainer SignIn. \n\nIf you have an account Choose below : \n\n"
                + "3.Trainee LogIn. \n4. Trainer LogIn To Modify Trainee Details.  \n5. HR Management. \n6. Store default trainee."
                + "\n7. Store default trainers.\n8. Exit");
            userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    userSignIn(userOption);
                    break;

                case 2:
                    userSignIn(userOption);
                    break;

                case 3:
                    logger.info("\n Enter the detials to access your account\n");
                    logger.info("Enter your name : ");
                    String traineeName = scanner.next();
                    logger.info("Enter yor email-id : ");
                    String traineeEmail = scanner.next();
                    DateUtil.timeDelay(500);
                    signinController.displayTraineeByNameAndEmail(traineeName, traineeEmail);
                    break;

                case 4:
                    logger.info("Enter User Name : ");
                    String logInName = scanner.next();
                    logger.info("Enter Password : ");
                    String logInPassword = scanner.next();
                    DateUtil.timeDelay(500);
                    signinController.trainerOperations(logInName, logInPassword, null, null);
                    break;
 
                case 5:
                    logger.info("Enter User Name : ");
                    String HrName = scanner.next();
                    logger.info("Enter Password :");
                    String HrPassword = scanner.next();
                    DateUtil.timeDelay(500);
                    signinController.hrOperationsByNameAndPassword(HrName, HrPassword);
                    break;
                
                case 6:
                    if (traineeService.defaultTrainees()) {
                        DateUtil.timeDelay(500);
                        logger.info("\nTrainees added successfully\n");
                    }
                    break;
 
                case 7:
                    if (trainerService.defaultTrainers()) {
                        DateUtil.timeDelay(500);
                        logger.info("\nTrainers added successfully\n");
                    }
                    break;

                default:
                    isFlow = false;
            }
        }       
    }
 
    /**
     * {@code userSignIn} common method for all employee to 
     * sign-in.
     *
     * @param user
     *          Which user (trainee/trainee) is going to sign-in.
     *
     * @since 1.0
     *
     */
    public void userSignIn(int user) {
        try {
            signinController.signIn(user);
        } catch (customException e) {
            logger.info("\n"+ e.getMessage() + "\nSorry you gave wrong information please try again !!! \n");
        }
    }
}
