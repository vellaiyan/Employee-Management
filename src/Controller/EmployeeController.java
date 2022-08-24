/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
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

public class EmployeeController {
    private static boolean isFlow = true;
    private EmployeeService EmployeeService = new EmployeeService();
    private SignInAndLogInController signinController = new SignInAndLogInController();

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
            System.out.println("1. Trainee SignIn. \n2. Trainer SignIn. \n\nIf you have an account Choose below : \n\n"
                + "3.Trainee LogIn. \n4. Trainer LogIn To Modify Trainee Details.  \n5. HR Management. \n6. Store default trainee."
                + "\n7. Store default trainers.\n8. Exit");
            userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    userSignIn(Constants.TRAINEE);
                    break;

                case 2:
                    userSignIn(Constants.TRAINER);
                    break;

                case 3:
                    System.out.println("\n Enter the detials to access your account\n");
                    System.out.println("Enter your name : ");
                    String traineeName = scanner.next();
                    System.out.println("Enter yor email-id : ");
                    String traineeEmail = scanner.next();
                    DateUtil.timeDelay(500);
                    //signinController.displayTraineeByNameAndEmail(traineeName, traineeEmail);
                    break;

                case 4:
                    System.out.print("Enter User Name : ");
                    String logInName = scanner.next();
                    System.out.print("Enter Password : ");
                    String logInPassword = scanner.next();
                    DateUtil.timeDelay(500);
                    //signinController.trainerOperations(logInName, logInPassword, null, null);
                    break;
 
                case 5:
                    System.out.print("Enter User Name : ");
                    String HrName = scanner.next();
                    System.out.print("Enter Password :");
                    String HrPassword = scanner.next();
                    DateUtil.timeDelay(500);
                    //signinController.hrOperationsByNameAndPassword(HrName, HrPassword);
                    break;
                
                case 6:
                    //if (traineeService.defaultTrainees()) {
                    //    DateUtil.timeDelay(500);
                    //    System.out.println("\nTrainees added successfully\n");
                    //}
                    //break;
 
                case 7:
                    //if (trainerService.defaultTrainers()) {
                    //    DateUtil.timeDelay(500);
                    //    System.out.println("\nTrainers added successfully\n");
                    //}
                    break;

                default:
                    isFlow = false;
            }
        }       
    }
 
    public void userSignIn(String userType) {
        try {
            signinController.signIn(userType);
        } catch (CustomException e) {
            System.out.println("\n"+ e.getMessage() + "\nSorry you gave wrong information please try again !!! \n");
        }
    }
}
