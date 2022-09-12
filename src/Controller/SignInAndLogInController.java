/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.controller;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.utils.Constants;
import com.ideas2it.utils.DateUtil;
import com.ideas2it.utils.ValidationUtil;

import java.text.DateFormat;  
import java.text.SimpleDateFormat; 
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;  
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.Scanner; 
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger; 

/**
 * The {@code SignInAndLogInController} class helps all employees to signin or login to their account. 
 * This class also provides modification support aswell. 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Showing details in table format.
 */

public class SignInAndLogInController { 
    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();
    private ProjectService projectService = new ProjectService();
    

    public void signIn(String userRole, String processToBeProceed, int employeeId) throws CustomException {
        EmployeeDto employeeDto = new EmployeeDto();   
        DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime localDate = LocalDateTime.now();     
        employeeDto.setFirstName(getInput("First Name", ValidationUtil.NAME_PATTERN));
        employeeDto.setSubject(getInput("Subject", ValidationUtil.NAME_PATTERN));
        employeeDto.setDateOfBirth(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter your date of birth (yyy-MM-dd)", "dob")));
        employeeDto.setDateOfJoining(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter your date of joining (yyyy-MM-dd)", "joining")));
        employeeDto.setBatch(Integer.parseInt((getInput("batch Number", ValidationUtil.EMPLOYEE_ID_PATTERN))));
        employeeDto.setGender(getInput("Gender", ValidationUtil.GENDER_PATTERN));
        employeeDto.setEmailId(getInput("Email-Id", ValidationUtil.EMAIL_PATTERN));
        employeeDto.setMobileNumber(Long.parseLong(getInput("Mobile Number", ValidationUtil.PHONE_PATTERN)));        
        employeeDto.setStatus();

        if (processToBeProceed == "add") {
            employeeDto.setCreateDate(localDate);
            employeeDto.setUpdateDate(localDate);
            boolean isAdded = employeeService.addEmployee(employeeDto, userRole);
            EmployeeController.logger.debug("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");                         
        } else if (processToBeProceed == "update") {
            employeeDto.setUpdateDate(localDate);
            boolean isUpdated = employeeService.updateEmployeeDetails(employeeDto, employeeId, userRole);
        } else {
            EmployeeController.logger.info("Enter the new role you want to assign for this employee. \n1. Trainee."
                +"\n2. Trainer. \n3. Project Manager. \n4. Human Resource");
            int newRole = scanner.nextInt();
            String role = "";
            switch(newRole) {
                case 1: 
                    role = Constants.TRAINEE;
                    break;
                case 2:
                    role = Constants.TRAINER;
                    break;
                case 3:
                    role = Constants.PROJECT_MANAGER;
                    break;
                case 4: 
                    role = Constants.HUMAN_RESOURCE;
                    break;
            }
                    
            boolean isUpdated = employeeService.updateEmployeeDetails(employeeDto, employeeId, role);
        }
    }

    public String  getDateOfBirthAndDateOfJoining(String input, String choosenDate) throws CustomException{
        boolean isValidDate = true;
        String dateOfBirthAndJoining = "";
        int remainingTimes = 0;

        for (int checkingLoop = 0; checkingLoop < 5; checkingLoop++) {
            dateOfBirthAndJoining = scanner.next();
            String date = DateUtil.validateDateOfBirth(dateOfBirthAndJoining, choosenDate);

            if (date == dateOfBirthAndJoining) {
                isValidDate = false;
                checkingLoop = 4;
                dateOfBirthAndJoining = date;
            } else if (date == "max") {
                EmployeeController.logger.info("Your date of birth exceeds current year");
                remainingTimes++;
            } else if (date == "low") { 
                EmployeeController.logger.error("Given date of birth is not valid");
                remainingTimes++;
            } else if (date == "min") {
                EmployeeController.logger.error("Your age is too low");
                remainingTimes++;
            } else if (date == "invalid") {
                EmployeeController.logger.error("Invalid dateOfBirth");
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
        for (int numberOfTimes = 0; numberOfTimes < 5; numberOfTimes++) {
                EmployeeController.logger.info("Enter Your " + inputType + " : ");
                String userInput = scanner.next();
                boolean isvalid = ValidationUtil.inputCheckingByRegex(regex, userInput);
                //Check the user input is valid or not.
                if (isvalid) {
                    initialUserInput = userInput;
                    numberOfTimes = 4;               
                } else if (!isvalid) {
                    EmployeeController.logger.error("Invalid " + inputType + "("+(5-(numberOfTimes+1)) + " more times)");
                    remainingTimes++;                                 
                } if (remainingTimes == 5){
                    throw new CustomException("Invalid");
                }
        }
        return initialUserInput;
    }   
    
    public void addOrUpdateProject(String userType, String processToBeProcced, int projectId) throws CustomException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(0);
        projectDto.setProjectName(getInput("Project Name", ValidationUtil.NAME_PATTERN));
        projectDto.setProjectDescription(getInput("Project Description", ValidationUtil.NAME_PATTERN));
        projectDto.setClientName(getInput("Client Name", ValidationUtil.NAME_PATTERN));
        projectDto.setCompanyName(getInput("Company Name", ValidationUtil.NAME_PATTERN));
        projectDto.setStartingDate(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter project starting date", "dob")));
        projectDto.setEstimatedEndingDate(LocalDate.parse(getDateOfBirthAndDateOfJoining("Estimated ending date", "joining"))); 
        projectDto.setStatus(ACTIVE_STATUS);
    
        if (processToBeProcced == "add") {
            projectService.addProject(projectDto);
        } else {
            projectService.updateProject(projectDto, projectId);
        }
        
    }

    public void trainerOperations(String name, String password) throws CustomException {
        Scanner scannerInput = new Scanner(System.in);
        boolean isContinue = true;
        if ((name == "ideas2it") && (password == "admin")) {
            EmployeeController.logger.info("1. View all trainee details. \n2. View all trainer details. \n3. View all project managers."
                +"\n4. Update employee details. \n5. Delete employee \n6. Update employee particular detail. \n7. View particular employee"
                +"\n8. View all assigned projects \n9. Display all employees. \n10. Display all projects. \n11. Exit");
            String userOption = scannerInput.next();
                switch (userOption) {
                    case "1":
                        try {
                            displayEmployeesByRole(Constants.TRAINEE);
                        } catch(CustomException customException) {
                            EmployeeController.logger.error(customException);
                        }
                        break;
 
                    case "2":
                        try {
                            displayEmployeesByRole(Constants.TRAINER);
                        } catch(CustomException customException) {
                            EmployeeController.logger.error(customException);
                        }
                        break;

                    case "3":
                         try {
                             displayEmployeesByRole(Constants.PROJECT_MANAGER);
                         } catch(CustomException customException) {
                             EmployeeController.logger.error(customException);
                         }
                         break;

                    case "4":
                         try {
                             updateEmployeeDetails();
                         } catch(CustomException customException) {
                             EmployeeController.logger.error(customException);
                         }
                         break;
                    
                    case "5":
                        try {
                            deleteEmployee();
                        } catch(CustomException customException) {
                            EmployeeController.logger.error(customException);
               
                        }

                        break;

                    case "6":
                        try {
                            EmployeeController.logger.info("Enter the user email id you want to update");
                            int employeeId= scanner.nextInt();
                            if (!employeeService.checkEmployeeById(employeeId)) {
                                EmployeeController.logger.error("Invalid email Id");
                            } else {
                                updateEmployeeDetail(employeeId);
                            }
                            
                        } catch (InputMismatchException inputMismatchException) {
                            EmployeeController.logger.error(inputMismatchException);
                        } catch (CustomException customException) {
                            EmployeeController.logger.error(customException);
                        }
                        break;

                    case "7":
                        try {
                            EmployeeController.logger.info("Enter the trainee Id you want to see");
                            int traineeId = scanner.nextInt();
                            if (!employeeService.checkEmployeeById(traineeId)) {
                                EmployeeController.logger.error("\nInvalid Employe Id\n");   
                            } else {
                                EmployeeController.logger.info(employeeService.getEmployeeById(traineeId));
                            }

                        } catch (InputMismatchException inputMismatchException) {
                            EmployeeController.logger.error(inputMismatchException);
                        } catch (CustomException customException) {
                            EmployeeController.logger.error(customException);
                        }
                        break;

                    case "8":
                        try {
                            boolean isValid= true;
                            while (isValid) {
                                EmployeeController.logger.info("Enter the project Id: ");
                                int projectId = scanner.nextInt();
                                /* if (projectService.checkProjectById(projectId)){
                                    List<EmployeeProjectDto> projects = projectService.getAssignedEmployeesById(projectId);
                                    displayAssignedProjects(projects);
                                    isValid = false;
                                } else {
                                    employeeController.logger.error("Project is not available. Enter valid project Id");  
                                } */
                            }
                        } catch (InputMismatchException inputMismatchException) {
                            throw new CustomException(inputMismatchException);
                        }
                        break;

                    case "9":
                        try {
                            displayAllEmployees(employeeService.getEmployees());
                        } catch (CustomException customException) {
                            EmployeeController.logger.error(customException);
                        }
                        break;
   
                    case "10" :
                        try {
                            displayAllProjects(projectService.getProjects());
                        } catch (CustomException customException) {
                            EmployeeController.logger.error(customException);
                        }
                        break;
                    
                    default:
                        isContinue = false;
                }        
        }
    } 

    public void displayAllEmployees(List<EmployeeDto> employeeDtos) throws CustomException {
        EmployeeController.logger.info("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%17s %8s %8s %15s %8s %15s %5s %15s %8s %15s %20s \n", "Id", "Batch", "First Name", 
            "Subject", "Gender", "Date Of Birth", "Date Of Joining", "Create Date", "Update Date", "email Id", "Mobile Number"); 
        EmployeeController.logger.info("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");  
        for (EmployeeDto employeeDto: employeeDtos) {
            EmployeeController.logger.info(employeeDto);
        }  
    }

    public void displayAllProjects(List<ProjectDto> projectDtos) {
        for (ProjectDto projectDto: projectDtos) {
            EmployeeController.logger.info(projectDto);
        }
    }
        
    public void displayEmployeesByRole(String userRole) throws CustomException {
        List<EmployeeDto> employees = employeeService.getEmployeesByRole(userRole);
        EmployeeController.logger.info("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%17s %8s %8s %15s %8s %15s %5s %15s %8s %15s %20s \n", "Id", "Batch", "First Name", 
            "Subject", "Gender", "Date Of Birth", "Date Of Joining", "Create Date", "Update Date", "email Id", "Mobile Number"); 
        EmployeeController.logger.info("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");  
        for (EmployeeDto employeeDto: employees) {
            EmployeeController.logger.info(employeeDto);
        }  
    }

    public void updateEmployeeDetails() throws CustomException {
        EmployeeController.logger.info("Enter the Employee Id you want to update");
        int employeeId = scanner.nextInt();
        if (employeeService.checkEmployeeById(employeeId)) {
            signIn("employee", "update", employeeId);
        } else {
            EmployeeController.logger.info("\n not available \n");
        }       
        
    }

    public void updateProjects() throws CustomException {
        EmployeeController.logger.info("Enter the project Id you want to update");
        int projectId = scanner.nextInt();
        if (projectService.checkIsProjectAvailableById(projectId)) {
            addOrUpdateProject(Constants.PROJECT_MANAGER, "update", 0);
        } else {
            EmployeeController.logger.info("\n Not available \n");
        }
    }
   
    public void deleteEmployee() throws CustomException{
        EmployeeController.logger.info("Enter the employee Id you want to delete");
        int employeeId = scanner.nextInt();
        if (employeeService.checkEmployeeById(employeeId)) {
            boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
            EmployeeController.logger.info("Employee deleted");
        } else {
            EmployeeController.logger.error("Not available\n");

        } 
    }

    public void updateEmployeeDetail(int employeeId) throws CustomException {
        EmployeeController.logger.info("Choose what details you want to change.\n1. Batch.\n2. First Name. \n3. Subject. \n4. Gender."
            + "\n5. Date of birth. \n6. Joining date. \n7. Email Id. \n8. Mobile Number.");
        int employeeChoice = scanner.nextInt();
        switch(employeeChoice) {
            case 1: 
                //employeeService.updateEmployeeDetail(employeeId, gettingUpdatedValue("First Name"), "first_name");
                break;
        }
    }

    public String gettingUpdatedValue(String value) {
        EmployeeController.logger.info("Enter your" + value + ":");
        String updatedInput = scanner.next();
        return updatedInput;
    }

    public void assignEmployees() throws CustomException {
        boolean isContinue = true;
        while (isContinue) {
            int projectId = validateAndGetProjectId();
            int employeeId = validateAndGetEmployeeId();
            LocalDate assignDate = LocalDate.parse(validateAndGetBothAssignedAndCompletionDate("assign"));
            LocalDate completionDate = LocalDate.parse(validateAndGetBothAssignedAndCompletionDate("completion"));
            LocalDate relievedOn = LocalDate.parse(validateAndGetBothAssignedAndCompletionDate("completion"));
            EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
            employeeProjectDto.setProjectId(projectId);
            employeeProjectDto.setEmployeeId(employeeId);
            employeeProjectDto.setAssignedOn(assignDate);
            employeeProjectDto.setCompletedOn(completionDate);
            employeeProjectDto.setRelievedOn(relievedOn);
            employeeProjectDto.setStatus(constants.ACTIVE_STATUS);
            
            projectService.assignProjectToEmployee(employeeProjectDto);
            EmployeeController.logger.info("Do you want to continue (y/n)");
            Scanner scanner1 = new Scanner(System.in);
            String userOption = scanner1.next();

            if (userOption == "n") {
                isContinue = false;
            }
        }
    }
                
    public int validateAndGetProjectId() {
        boolean isValidProjectId = true;
        int projectId;
        Scanner scanner1 = new Scanner(System.in);
        while(isValidProjectId) {
            EmployeeController.logger.info("Enter the project Id");
            try {
                projectId = scanner1.nextInt();
                if (projectService.checkIsProjectAvailableById(projectId)) {
                    isValidProjectId = false;
                    return projectId;
                } else {
                    EmployeeController.logger.error("Enter correct project Id");
                } 
            } catch (CustomException customException) {
                EmployeeController.logger.error("You gave wrong project Id please give correct input");
            }

        }
        return 0;
    }
  
    public int validateAndGetEmployeeId() {
        boolean isValidEmployeeId = true;
        int employeeId;
        while (isValidEmployeeId) {
            EmployeeController.logger.info("Enter EmployeeId want to assign");
            try {
                employeeId = scanner.nextInt();
                if (employeeService.checkEmployeeById(employeeId)) {
                    isValidEmployeeId = false;
                    return employeeId;
                }
            } catch (CustomException customException) {
                EmployeeController.logger.error("You gave wroing input please give correct input");
                validateAndGetEmployeeId();
            }

        }
        return 0;
    }

    public String validateAndGetBothAssignedAndCompletionDate(String modeOfValidation) {
        boolean isValidDate = true;

        while (isValidDate) {
            if (modeOfValidation == "assign") {
                EmployeeController.logger.info("Enter the date of assigning (yyyy-MM-dd)");
                String assignDate = scanner.next();
                String result = DateUtil.validateAssignDateAndCompletionDate(assignDate, "assign");
                if (result == "Not valid") {
                    EmployeeController.logger.error("Invalid date");
                    validateAndGetBothAssignedAndCompletionDate("assign");
                } else {
                    return assignDate;
                }
            } else {
                EmployeeController.logger.info("Enter the date of estimated completion date (yyyy-MM-dd)");
                String completionDate = scanner.next();
                String result = DateUtil.validateAssignDateAndCompletionDate(completionDate, "completion");
                if (result == "Not valid") {
                    EmployeeController.logger.error("Invalid date");
                    validateAndGetBothAssignedAndCompletionDate("completion");
                } else {
                    return completionDate;
                }
            }
        }    
 
        return "";       
    } 
}