/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.controller;

import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.HumanResource;
import com.ideas2it.service.TraineeService;
import com.ideas2it.service.TrainerService;
import com.ideas2it.service.HumanResourceService;
import com.ideas2it.utilitis.ValidationUtil;
import com.ideas2it.utilitis.DateUtil;
import com.ideas2it.exception.customException;
 
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.Appender; 
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.SimpleLayout;

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
    private int age;
    private String city;
    private String taluk;
    private String state;
    private String domain;
    private String doorNo;
    private String gender;
    private String pinCode;
    private String emailId;
    private String lastName;
    private String district;
    private int trainee = 1;
    private int trainer = 2;
    private String firstName;
    private String fatherName;
    private String motherName;
    private String experience;
    private String batch = "";
    private String dateOfBirth;
    private String mobileNumber;
    private short employeeId = 127;
    private int modifiedTrainee = 3;
    private int modifiedTrainer = 4;
    private boolean isContinue = true;

    private Scanner scanner = new Scanner(System.in);
    private TraineeService traineeService = new TraineeService();
    private TrainerService trainerService = new TrainerService();
    private HumanResourceService humanResourceService = new HumanResourceService();
    private static Logger logger = Logger.getLogger(SignInAndLogInController.class);
 
    /**
     * {@code signIn} implemented to sign-in for both trianee and trainer.
     *
     * @param user
     *          Decides which user is going to sign-in (trainer/trainee).
     * 
     * @since 1.0
     *
     */
    public void signIn(int user) throws customException{
        try{
            firstName = getInput("First Name", ValidationUtil.NAME_PATTERN);
            lastName = getInput("Last Name", ValidationUtil.NAME_PATTERN);
            //check if user is trainer or modified trainer to get their details.
            if ((user == trainer) || (user == modifiedTrainer)) {
                domain = getInput("Subject", ValidationUtil.NAME_PATTERN);
                experience = getInput("Experience", ValidationUtil.EMPLOYEE_ID_PATTERN);
             }         
            dateOfBirth = getDateOfBirth();
            //check if user is trainee or modified trainee to get their details.
            if ((user == trainee) || (user == modifiedTrainee)) {
                batch = getInput("batch Number", ValidationUtil.EMPLOYEE_ID_PATTERN);
            }
            fatherName = getInput("Father Name", ValidationUtil.NAME_PATTERN);
            motherName = getInput("Mother Name", ValidationUtil.NAME_PATTERN);
            gender = getInput("Gender", ValidationUtil.GENDER_PATTERN);
            emailId = getInput("Email-Id", ValidationUtil.EMAIL_PATTERN);
            mobileNumber = getInput("Mobile Number", ValidationUtil.PHONE_PATTERN);
            doorNo = getInput("Door Number", ValidationUtil.DOOR_PATTERN);
            city = getInput("City Name", ValidationUtil.NAME_PATTERN);
            taluk = getInput("Taluk", ValidationUtil.NAME_PATTERN);
            state = getInput("State", ValidationUtil.NAME_PATTERN);
            district = getInput("District", ValidationUtil.NAME_PATTERN);
            pinCode = getInput("PinCode", ValidationUtil.PIN_PATTERN);
        } catch(customException e) {
            throw new customException("invalid input");
        }
        short id = employeeId;
        employeeId++;
        int pin = Integer.valueOf(pinCode); 
        long mobile = Long.valueOf(mobileNumber); 
        age = DateUtil.age;  
        //If user is trainee then pass details to trainee service to add new trainee.
        if (user == trainee) {
            byte trainingBatch = Byte.valueOf(batch);
            if (traineeService.addTrainee(firstName, lastName, id, trainingBatch, dateOfBirth, fatherName, motherName, gender, emailId, mobile,
                    doorNo, city, taluk, state, district, pin, age)) {
                logger.info("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");                
            }
        }
        //If user is trainee then pass details to trainee service to add new trainee.
        if (user == trainer) {
            if(trainerService.addTrainer(firstName, lastName, id, domain, experience, dateOfBirth, fatherName, motherName, gender,
                    emailId, mobile, doorNo, city, taluk, state, district, pin, age)) {
                logger.info("Hi Mam/Sir Your details are saved successfully\n To access Trainee and Trainer details you "
                    +"have to verfy username and password");                

            }

            logger.info("Enter usernme : ");
            String validName = scanner.next();
            logger.info("Enter Your password : ");
            String validPassword= scanner.next();
            trainerOperations(validName, validPassword, firstName, emailId);
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
            logger.info("Enter your date of birth(dd-MM-yyyy) : ");
            dateOfBirth = scanner.next();
            String date = DateUtil.dateOfBirthValidation(dateOfBirth);
            //Check the user input is valid or not.
            if (date.equals(dateOfBirth)) {
                isValidDate = false;
                checkingLoop = 4;
                dateOfBirth = date;
            } else if (date.equals("max")) {
                logger.info("Your date of birth exceeds current year");
                remainingTimes++;
            } else if (date.equals("low")) { 
                logger.info("Given date of birth is not valid");
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
    
    /**
     * {@code trainerOperations} implemented to show all the trainer options
     * to perform.
     *
     *
     * @param name
     *          User Name to validate.
     *
     * @param password
     *          Password to validate.
     *
     * @param firstName
     *          First Name of the trainer.
     *
     * @param email
     *          Email-id of the trainer.
     *
     * @since 1.0
     *
     */
    public void trainerOperations(String name, String password, String firstName, String email) { 
        //Validating trainer using predefined user name and password.
        if (name.equals("ideas2it") && password.equals("admin")) {
            //Trainer use this option ten times only.
            for (int numberOfTimes = 0; numberOfTimes < 20; numberOfTimes++) {
                System.out.println("\n1. Show all Trainees \n2. Show particular Trainee \n3. Show all trainers."
                    +"\n4. Show your details\n5. Delete Trainee.\n6. Delete Trainer.\n7. Modify Trainee."
                    +"\n8. Modify trainer. \n9. Change Trainee particular data\n10. Exit");
                int trainerOption = scanner.nextInt();
                switch (trainerOption) {
                    case 1:
                        System.out.println();
                        List<Trainee> Trainees = traineeService.getTrainees();
                        displayTrainees(Trainees, "trainer");
                        break;

                    case 2:
                        System.out.println("Enter Trainee name : ");
                        String traineeName = scanner.next();
                        System.out.println("Enter Trainee email employeeId : ");
                        String traineeEmail = scanner.next();
                        displayTraineeByNameAndEmail(traineeName, traineeEmail);
                        break;

                    case 3:
                        System.out.println();
                        List<Trainer> trainers = trainerService.getTrainers();
                        displayTrainers(trainers, "trainer");
                        break;

                    case 4:
                        try {
                            Trainer trainer = trainerService.getTrainerByNameAndEmail(firstName, email);
                            displayTrainer(trainer);
                        } catch(NullPointerException e) {
                            System.out.println("\nDetails not found\n");
                        }
                        break;

                    case 5:
                        commonDeletion("Trainee");
                        break;

                    case 6:
                        commonDeletion("Trainer");
                        break;
  
                    case 7:
                        modifyTrainee();
                        break;

                    case 8:
                        modifyTrainer();
                        break;

                    case 9:
                        System.out.println("Enter the Trainee name you want to modify : ");
                        String modifiedName = scanner.next();
                        System.out.println("Enter the Email-employeeId of the Trainee you wan to modify : ");
                        String modifiedEmail = scanner.next();
                        Trainee trainee = traineeService.getTraineeByNameAndEmail(modifiedName, modifiedEmail);
                        updateTraineeDetail(trainee);
                        break;

                    case 10:
                        System.out.println("\n");
                        numberOfTimes = 10;
                        break;
                }    
            }
        } else {
            System.out.println("\nValidation failed\n");
        }
    }

    /**
     * {@code displayTraineeByNameAndEmail} display the particular trainee 
     * by validating his/her name and email-id.
     *
     * @param name
     *          Name of the trainee to be validate and display.
     *
     * @param email
     *          Email-Id of the trainee to be validate and display.
     * 
     * @since 1.0
     *
     */
    public void displayTraineeByNameAndEmail(String name, String email) {
        Trainee trainee = traineeService.getTraineeByNameAndEmail(name, email);
        System.out.println("--------------------------------------------------------------------"
            + "------------------------------------------------------------------------------");  
        System.out.printf("%5s %8s %8s %13s %12s %12s %15s %17s %10s %7S %7s %10s %4s", "EMPLOYEE ID","BATCH", 
            "NAME", "GENDER", "DOB", "AGE", "E-MAIL ID", "MOBILE-NO", "DOOR-NO", "CITY", "TALUK", "PINCODE", "|");  
        System.out.println();  
        System.out.println("--------------------------------------------------------------"
            +"------------------------------------------------------------------------------------");
        if (trainee == null) {
            System.out.println("\nDetails not found\n");
        } else {
            System.out.println(trainee);
        }
        System.out.println("------------------------------------------------------------------------"
            +"--------------------------------------------------------------------------");      
    }

    /**
     * {@code displayTrainees} display all the trainees from trainees list.
     *
     * @since 1.0
     *
     */
    private void displayTrainees(List<Trainee> trainees, String user) {
	boolean isAvailable = false;
        if (user.equals("trainer")) {
            System.out.println("-------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------");  
            System.out.printf("%5s %8s %8s %13s %10s %12s %15s %20s %10s %7S %7s %9s %5s", "EMPLOYEE ID","BATCH", 
                "NAME", "GENDER", "DOB", "AGE", "E-MAIL ID", "MOBILE-NO", "DOOR-NO", "CITY", "TALUK", "PINCODE", "|");  
            System.out.println();  
            System.out.println("--------------------------------------------------------------"
                +"------------------------------------------------------------------------------------");
            for (Trainee trainee : trainees) {		
                isAvailable = true;
                System.out.println(trainee);
 
            }
            System.out.println("-----------------------------------------------------------------------"
                +"---------------------------------------------------------------------------");        
            if (!isAvailable) {
                System.out.println("\nDetails not found\n");
            }
        }

        if (user.equals("hr")) {
                System.out.println("--------------------------------"); 
                System.out.printf("%5s %10s", "EMPLOYEE ID", "NAME");  
                System.out.println("");  
                System.out.println("--------------------------------"); 
                for(Trainee trainee : trainees) {
                    isAvailable = true;
                    System.out.format("%7s %14s", trainee.getEmployeeId(), trainee.getFirstName());
                    System.out.println(""); 
        } 
        System.out.println("--------------------------------");             
        if (isAvailable) {
            System.out.println("\nDetails not found\n");
        }
        }
    }

    /**
     * {@code displayTrianer} display the particular trainer
     *
     * @param trainer
     *          Trainer object to be display
     * 
     * @since 1.0
     *
     */
    public void displayTrainer(Trainer trainer) {
        System.out.println("--------------------------------------------------------------------"
            + "---------------------------------------------------------------------------------------");  
        System.out.printf("%5s %8s %12s %12s %8s %10s %11s %13s %15s %12s %7s %8s %11s %4s", "EMPLOYEE ID","NAME", 
            "SUBJECT", "EXPERIENCE", "GENDER", "DOB", "AGE", "E-MAIL ID", "MOBILE-NO", "DOOR-NO", "CITY", "TALUK", "PINCODE", "|");  
        System.out.println();  
        System.out.println("-------------------------------------------------------------------"
            +"----------------------------------------------------------------------------------------");
        if (trainer == null) {
            System.out.println("\nDetails not found\n");
        } else {      
            System.out.println(trainer);
        }
        System.out.println("-------------------------------------------------------------------"
            +"----------------------------------------------------------------------------------------");
    }

    /**
     * {@code displayTrainers} display all the trainers from trainers list.
     *
     * @param trianers
     *          List of trainers to be display.
     *
     * @param user
     *          This method common for {@cod trainerOperations} and
     *          {@code HumarResourceOperations} will decide trainer/Hr.
     * 
     * @since 1.0
     *
     */
    private void displayTrainers(List<Trainer> trainers, String user) {
	boolean isAvailable = false;
            if (user.equals("trainer")) {
                System.out.println("--------------------------------------------------------------------"
                   + "--------------------------------------------------------------------------------------------");  
                System.out.printf("%5s %8s %12s %12s %8s %10s %11s %13s %18s %12s %7s %8s %11s %5s", "EMPLOYEE ID","NAME", 
                    "SUBJECT", "EXPERIENCE", "GENDER", "DOB", "AGE", "E-MAIL ID", "MOBILE-NO", "DOOR-NO", "CITY", "TALUK", "PINCODE", "|");  
                System.out.println();  
                System.out.println("-------------------------------------------------------------------"
                    +"---------------------------------------------------------------------------------------------");
                for (Trainer trainer : trainers) {		
                    isAvailable = true;
                    System.out.println(trainer);
                } 
                System.out.println("-------------------------------------------------------------------"
                    +"---------------------------------------------------------------------------------------------");
                if (!isAvailable) {
                    System.out.println("\nDetails not found\n");
                }
            }

            if (user.equals("hr")) {
                System.out.println("--------------------------------");  
                System.out.printf("%5s %10s", "EMPLOYEE ID", "NAME");  
                System.out.println("");  
                System.out.println("--------------------------------");  
                for(Trainer trainer : trainers) {
                    isAvailable = true;
                    System.out.format("%7s %14s", trainer.getEmployeeId(), trainer.getFirstName());
                    System.out.println(); 
            } 
            System.out.println("--------------------------------");             
            if (!isAvailable) {
                System.out.println("\nDetails not found\n");
            }
        }
    }
 
     /**
     * {@code commonOutput} common method to print deletion status.
     *
     * @param isValid
     *          Decides the deletion status.
     * 
     * @since 1.0
     *
     */
    public void commonOutput(boolean isValid) {
        if (isValid) {
            System.out.println("\nDeletion successful\n");
        } else {
            System.out.println("\nDetails not found\n");
        }
    }

    /**
     * {@code hrOperationsByNameAndPassword} implementd for human resource
     * to perform his/her operations.
     *
     * @param name
     *          User name for HR validation.
     *
     * @param password
     *          Password for HR validation.
     * 
     * @since 1.0
     *
     */
    public void hrOperationsByNameAndPassword(String name, String password) { 
        if (name.equals("ideas2it") && password.equals("admin")) {
            Map<String, LinkedList<HumanResource>> mappedTrainees = new LinkedHashMap<String, LinkedList<HumanResource>>();
            while (isContinue) {
                System.out.println("\n1. Split team by manual. \n2. Show all teams.\n3. Exit");   
                int hrOption = scanner.nextInt();        
                switch(hrOption) {
                    case 1:
                        splitTeamByManual(mappedTrainees);
                        break;

                    case 2:
                        displayAllTeams(mappedTrainees);
                        break;
                         
                    case 3:
                        isContinue = false;
                        System.out.println();
                        break;
                }                                     
            }
        } else {
        System.out.println("Validation Failed\n ");
        }
    }

    /**
     * {@code SplitTeamByManual} Implemented for HR to split team manually.
     *
     * @param mappedList
     *          Store all the mapped trainers and trainees.
     *
     * 
     * @since 1.0
     *
     */
    public void splitTeamByManual(Map<String, LinkedList<HumanResource>> mappedList) {
        boolean isContinue = true;
        String trainerId ;
        String traineeId;
        boolean isTrainerPresent = true;
        boolean isTraineePresent = true;
        boolean isContinueTrainee = true;
        boolean isContinueTrainer = true;
        List<Trainee> trainees = traineeService.getTrainees();
        List<Trainer> trainers = trainerService.getTrainers();
        System.out.println("Here you can see all trainees and trainers details\n Enter the trainer and id you want to assign"
            + "students for him");
        System.out.println("\nAll Trainers with Employee-Id");
        displayTrainers(trainers, "hr");
        System.out.println("\nAll Trianees with Employee-Id");
        displayTrainees(trainees, "hr");
        
        do {
            do {
                System.out.println("Enter the trainer Id you want to assign trainees");
                trainerId = scanner.next();
                boolean isTrainerAvailable = checkIsTrainerById(trainerId); 
                isTrainerPresent = isTrainerAvailable;
            } while (isTrainerPresent);
            boolean isNeed = true;
            do{                
                do {
                    System.out.println("Enter the trainee Id you want to assign for " + trainerId);
                    traineeId = scanner.next();
                    isContinueTrainee = true;
                    boolean isTrainee = traineeService.checkIsTraineeById(traineeId);

                     if (isTrainee) {
                         HumanResource humanResource = new HumanResource(traineeId);
                         humanResourceService.addTrainees(humanResource, isNeed);
                         isNeed = false;
                         isTraineePresent = false;
                     } else {
                         System.out.println("Enter the trainee Id you want to assign for " + traineeId);
                     }
                } while (isTraineePresent);

                System.out.println("\nDo you want to add another trainee for " + trainerId + "(y/n)");
                String trainerSuggesstion = scanner.next();
                if (trainerSuggesstion.toLowerCase().equals("n")) {
                    isContinueTrainee = false;
                } 
            } while (isContinueTrainee);
            LinkedList<HumanResource> assignedTrainees = humanResourceService.getAssignedTrainees(); 
            mappedList.put(trainerId, assignedTrainees);
            System.out.println("Do you want to continue to assign trainees for traineers (y/n)");
            String traineeSuggestion = scanner.next();
            if (!traineeSuggestion.toLowerCase().equals("y")) {
                isContinueTrainer = false;
            }
            
        } while (isContinueTrainer);
    }

    /**
     * {@code displayAllTeams} display all mapping teams.
     *
     * @param mappedList
     *          List of mappint trainers and trainees.
     *
     * 
     * @since 1.0
     *
     */
    public void displayAllTeams(Map<String, LinkedList<HumanResource>> mappedList) {
        int teamCount = 1;
        for(Map.Entry<String, LinkedList<HumanResource>> entry: mappedList.entrySet()) {
            System.out.println("------------------------------------------------------------------------------- Team " + teamCount 
                +"-------------------------------------------------------------------------------------  ");
            System.out.println("TRAINER");
            System.out.println("--------------------------------------------------------------------"
                + "-----------------------------------------------------------------------------------");  
            System.out.printf("%5s %8s %8s %13s %12s %12s %15s %17s %10s %7S %7s %10s %4s", "EMPLOYEE ID","NAME", 
                "SUBJECT", "EXPERIENCE", "GENDER", "DOB", "AGE", "E-MAIL ID", "MOBILE-NO", "DOOR-NO", "CITY", "TALUK", "PINCODE", "|");  
            System.out.println();  
            System.out.println("---------------------------------------------------------------"
                +"----------------------------------------------------------------------------------------");

            Trainer trainer = trainerService.getTrainerByEmployeeId(entry.getKey());
            if (trainer == null) {
                System.out.println("\nDetails not found\n");
            } else {
                System.out.println(trainer);
                System.out.println("---------------------------------------------------------------"
                    +"----------------------------------------------------------------------------------------");

            }
            List<HumanResource> trainerIds = entry.getValue();
            System.out.println("\nTRAINEES");
            System.out.println("--------------------------------------------------------------------"
                + "------------------------------------------------------------------------------");  
            System.out.printf("%5s %8s %8s %13s %12s %12s %15s %17s %10s %7S %7s %10s %4s", "EMPLOYEE ID","BATCH", 
                "NAME", "GENDER", "DOB", "AGE", "E-MAIL ID", "MOBILE-NO", "DOOR-NO", "CITY", "TALUK", "PINCODE", "|");  
            System.out.println();  
            System.out.println("---------------------------------------------------------------"
                +"-----------------------------------------------------------------------------------");
            for (HumanResource humanResource: trainerIds) {
                Trainee trainee = traineeService.getTraineeByEmployeeId(humanResource.getEmployeeId());
                if (trainee == null) {
                    System.out.println("\nDetails not found\n");
                } else {
                    System.out.println(trainee);
                    System.out.println("---------------------------------------------------------------"
                        +"-----------------------------------------------------------------------------------\n");                   
                }                    
            }   
            teamCount++;    
        }                                                                                                                                                    
    }

    /**
     * {@code checkIsTrainerById} check the trainer if he/she is available
     * in the trainers list.
     *
     * @param trainerId
     *          Trainer-Id to be checked.
     *
     * @return true if trainer present otherwise false.
     * 
     * @since 1.0
     *
     */
    public boolean checkIsTrainerById(String trainerId) {
        boolean isTrainerPresent = true;
        boolean isTrainer = trainerService.checkIstrainerById(trainerId);
        if (isTrainer) {
            isTrainerPresent = false;
        } else {
            isTrainerPresent = true;
            System.out.println("Invalid trainer Id");
        }
        return isTrainerPresent;
    }

    /**
     * {@code modifyTrainee} to modify trainee all details.
     * 
     * @since 1.0
     *
     */
    public void modifyTrainee() {
        System.out.println("Enter Trainee name you want to modify : ");
        String traineName = scanner.next();
        System.out.println("Enter Trainee email employeeId : ");
        String traineEmail = scanner.next();
        int traineeIndex = traineeService.getTraineeIndexByNameAndEmail(traineName, traineEmail);
        if (traineeIndex >= 0 ) {
            System.out.println("\nEnter all modified Details\n");                            
            try {
                signIn(3);
                short traineeEmployeeId = Short.valueOf(employeeId); 
                int traineePinCode = Integer.valueOf(pinCode); 
                long traineeMobileNumber = Long.valueOf(mobileNumber);
                byte trainingBatch = Byte.valueOf(batch);
                if (traineeService.updateTraineeByIndex(firstName, lastName, traineeEmployeeId, trainingBatch, 
                        dateOfBirth, fatherName, motherName, gender, emailId, traineeMobileNumber,
                        doorNo, city, taluk, state, district, traineePinCode, age, traineeIndex)) {
                    System.out.println("\n Modified Successful\n");
                }
            } catch(customException e) {
                    System.out.println("\nSorry invalemployeeId input.\n");
            }			   
        } else {
            System.out.println("\n Details not found\n");
        }
    }

    public void commonDeletion(String user) {
        System.out.println("Enter the" +  user + " name you want to delete : ");
        String nameToDelete = scanner.next();
        System.out.println("Enter the" + user + " email-employeeId your want to delete : ");
        String emailToDelete = scanner.next();
        if (user.equals("Trainee")) {
            boolean isTrainee = traineeService.deleteTraineeByNameAndEmail(nameToDelete, emailToDelete);
            commonOutput(isTrainee);
        } else {
            boolean isTrainer = trainerService.deleteTrainerByNameAndEmail(nameToDelete, emailToDelete);
            commonOutput(isTrainer);           
        }

    }

    /**
     * {@code modifyTrainer} to modify trainer all details.
     * 
     * @since 1.0
     *
     */
    public void modifyTrainer() {
        System.out.println("Enter Trainer name you want to modify : ");
        String modifiedTrainerName = scanner.next();
        System.out.println("Enter Trainer email employeeId : ");
        String modifiedTrainerEmail = scanner.next();
        int trainerIndex = trainerService.getTrainerIndexByNameAndEmail(modifiedTrainerName, modifiedTrainerEmail);
        if (trainerIndex >= 0 ) {
            try {
                signIn(4);
                short trainerEmployeeId = Short.valueOf(employeeId); 
                int trainerPinCode= Integer.valueOf(pinCode); 
                long trainerMobileNumber = Long.valueOf(mobileNumber);
                age = DateUtil.age;
                if (trainerService.upateTrainerByIndex(firstName, lastName, trainerEmployeeId, domain, experience, dateOfBirth,                                                     fatherName, motherName, gender, emailId, trainerMobileNumber, doorNo, city, taluk, state, district, trainerPinCode,                                          age,trainerIndex)) {
                    System.out.println("\n Modified Successful\n");
                }
            } catch(customException e) {
                    //throw new customException("invalid input");
                    System.out.println("\nInvalemployeeId input.\n");
            }
        } else { 
            System.out.println("\n Details not found\n");
        }
    }

    /**
     * {@code updateTraineeDetails} to update trainee particular detail.
     *
     * @param trainee
     *          Trainee object to be updated.
     * 
     * @since 1.0
     *
     */
    private void updateTraineeDetail(Trainee trainee) {
        if (trainee == null) {
            System.out.println("\n Details not found ");
        } else {
            System.out.println ("please select which one you are going to change " );
            System.out.println ("\n1.First Name.\n2. Last Name. \n3. date Of Birth \n4. Father Name. \n5. Mother Name."
                    + "\n6. Gender. \n7. Email-employeeId. \n8. Mobile Number. \n9. DoorNo. \n10. City. \n11. Taluk. \n12. State."
                    + "\n13. District. \n14. Pin Code. \n15. Exit");
            int changeOption = scanner.nextInt();   
         
            switch (changeOption) {
                case 1: 
                    try {
                        String firstName = getInput("First Name", "namePattern");
                        trainee.setFirstName(firstName);
                    } catch (customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

		case 2:
                    try {
                        String lastName = getInput("Last Name", "namePattern");
                        trainee.setLastName(lastName);
                    } catch (customException e) {
                        
                    }
                    break;

                case 3:
                    try {
                        String dateOfBirth = getDateOfBirth();
                        trainee.setDateOfBirth(dateOfBirth);
                        age = DateUtil.age;
                        trainee.setAge(age);
                    } catch(customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 4:
                    try {
                        String fatherName = getInput("Father Name", "namePattern");
                        trainee.setFatherName(fatherName);
                    } catch(customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 5:
                    try {
                        String motherName = getInput("mother name", "namePattern");
                        trainee.setMotherName(motherName);
                    } catch(customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 6:
                    try {
                        String gender = getInput("Gender", "namePattern");
                        trainee.setGender(gender);
                    } catch (customException e) {
                        System.out.println("Invalid input");
                    }
                    break;

                case 7:
                    try {
                        String emailId = getInput("Email-Id", "emailPattern");
                        trainee.setEmailId(emailId);
                    } catch (customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 8: 
                    long mobileNumber = scanner.nextLong();
                    trainee.setMobileNumber(mobileNumber);
                    break;

                case 9:
                    try {                
                        String doorNo = getInput("Door Number", "doorPattern");
                        trainee.setDoorNumber(doorNo);
                    } catch (customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 10:
                    try {
                        String city = getInput("City", "namePattern");
                        trainee.setCity(city);
                    } catch (customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 11: 
                    try { 
                        String taluk = getInput("Taluk", "namePattern");
                        trainee.setTaluk(taluk);
                    } catch (customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 12:
                    try {
                        String state = getInput("State", "namePattern");
                        trainee.setState(state);
                    } catch (customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 13:
                    try {
                        String district = getInput("District", "namePattern");
                        trainee.setDistrict(district);
                    } catch(customException e) {
                        System.out.println("Invalid Input");
                    }
                    break;

                case 14:
                    int pinCode = scanner.nextInt();
                    trainee.setPinCode(pinCode);
                    break;
                    
                default:
                    System.out.println("Thank You");                
            }
        }
    }
}
