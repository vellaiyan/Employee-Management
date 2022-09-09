/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.controller;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.ProjectService;
import com.ideas2it.utilitis.Constants;
import com.ideas2it.utilitis.DateUtil;
import com.ideas2it.utilitis.ValidationUtil;
 
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;  

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
        employeeDto.setFirstName(getInput("First Name", ValidationUtil.NAME_PATTERN));
        employeeDto.setSubject(getInput("Subject", ValidationUtil.NAME_PATTERN));
        employeeDto.setDateOfBirth(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter your date of birth (yyy-MM-dd)", "dob")));
        employeeDto.setDateOfJoining(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter your date of joining (yyyy-MM-dd)", "joining")));
        employeeDto.setBatch(Integer.parseInt((getInput("batch Number", ValidationUtil.EMPLOYEE_ID_PATTERN))));
        employeeDto.setGender(getInput("Gender", ValidationUtil.GENDER_PATTERN));
        employeeDto.setEmailId(getInput("Email-Id", ValidationUtil.EMAIL_PATTERN));
        employeeDto.setMobileNumber(Long.parseLong(getInput("Mobile Number", ValidationUtil.PHONE_PATTERN)));
        employeeDto.setCreateDate(LocalDate.parse("2000-02-28"));
        employeeDto.setUpdateDate(LocalDate.parse("2000-02-28"));        
        employeeDto.setStatus("active");

        if (processToBeProceed.equals("add")) {
            boolean isAdded = employeeService.addEmployee(employeeDto, userRole);
            System.out.println("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");                         
        } else {
            System.out.println("Enter the new role you want to assign for this employee. \n1. Trainee."
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
        //Asking input from user five times if the given input is invalid.
        for (int checkingLoop = 0; checkingLoop < 5; checkingLoop++) {
            System.out.println(input);
            dateOfBirthAndJoining = scanner.next();
            String date = DateUtil.validateDateOfBirth(dateOfBirthAndJoining, choosenDate);
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
    
    public void addOrUpdateProject(String userType, String processToBeProcced, int projectId) throws CustomException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectName(getInput("Project Name", ValidationUtil.NAME_PATTERN));
        projectDto.setProjectDescription(getInput("Project Description", ValidationUtil.NAME_PATTERN));
        projectDto.setClientName(getInput("Client Name", ValidationUtil.NAME_PATTERN));
        projectDto.setCompanyName(getInput("Company Name", ValidationUtil.NAME_PATTERN));
        projectDto.setStartingDate(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter project starting date", "dob")));
        projectDto.setEstimatedEndingDate(LocalDate.parse(getDateOfBirthAndDateOfJoining("Estimated ending date", "joining"))); 
        projectDto.setDeleteStatus("Active");
    
        if (processToBeProcced.equals("add")) {
            projectService.addProject(projectDto);
        } else {
            projectService.updateProject(projectDto, projectId);
        }
        
    }

    public void trainerOperations(String name, String password) throws CustomException {
        Scanner scannerInput = new Scanner(System.in);
        boolean isContinue = true;
        if (name.equals("ideas2it") && password.equals("admin")) {
            System.out.println("1. View all trainee details. \n2. View all trainer details. \n3. View all project managers."
                +"\n4. Update employee details. \n5. Delete employee \n6. Update employee particular detail. \n7. View particular employee"
                +"\n8. View all assigned projects \n9. Display all employees. \n10. Display all projects. \n11. Exit");
            String userOption = scannerInput.next();
                switch (userOption) {
                    case "1":
                        try {
                            displayEmployeesByRole(Constants.TRAINEE);
                        } catch(CustomException customException) {
                            System.out.println(customException);
                        }
                        break;
 
                    case "2":
                        try {
                            displayEmployeesByRole(Constants.TRAINER);
                        } catch(CustomException customException) {
                            System.out.println(customException);
                        }
                        break;

                    case "3":
                         try {
                             displayEmployeesByRole(Constants.PROJECT_MANAGER);
                         } catch(CustomException customException) {
                             System.out.println(customException);
                         }
                         break;

                    case "4":
                         try {
                             updateEmployeeDetails();
                         } catch(CustomException customException) {
                             System.out.println(customException);
                         }
                         break;
                    
                    case "5":
                        try {
                            deleteEmployee();
                        } catch(CustomException customException) {
                            System.out.println(customException);
               
                        }

                        break;

                    case "6":
                        try {
                            System.out.println("Enter the user email id you want to update");
                            int employeeId= scanner.nextInt();
                            if(!employeeService.checkEmployeeById(employeeId)) {
                                System.out.println("Invalid email Id");
                            } else {
                                updateEmployeeDetail(employeeId);
                            }
                            
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println(inputMismatchException);
                        } catch (CustomException customException) {
                            System.out.println(customException);
                        }
                        break;

                    case "7":
                        try {
                            System.out.println("Enter the trainee Id you want to see");
                            int traineeId = scanner.nextInt();
                            if (!employeeService.checkEmployeeById(traineeId)) {
                                System.out.println("\nInvalid Employe Id\n");   
                            } else {
                                System.out.println(employeeService.getEmployeeById(traineeId));
                            }

                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println(inputMismatchException);
                        } catch (CustomException customException) {
                            System.out.println(customException);
                        }
                        break;

                    case "8":
                        try {
                            boolean isValid= true;
                            while (isValid) {
                                System.out.println("Enter the project Id: ");
                                int projectId = scanner.nextInt();
                                /* if(projectService.checkProjectById(projectId)){
                                    List<EmployeeProjectDto> projects = projectService.getAssignedEmployeesById(projectId);
                                    displayAssignedProjects(projects);
                                    isValid = false;
                                } else {
                                    System.out.println("Project is not available. Enter valid project Id");  
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
                            System.out.println(customException);
                        }
                        break;
   
                    case "10" :
                        try {
                            displayAllProjects(projectService.getProjects());
                        } catch (CustomException customException) {
                            System.out.println(customException);
                        }
                        break;
                    
                    default:
                        isContinue = false;
                }        
        }
    } 

    public void displayAllEmployees(List<EmployeeDto> employeeDtos) throws CustomException {
        System.out.println("---------------------------------------------------------------------------------"
            +"------------------------------------------------------------------------------------------");  
        System.out.format("%17s %8s %8s %15s %8s %15s %5s %15s %8s %15s %20s \n", "Id", "Batch", "First Name", 
            "Subject", "Gender", "Date Of Birth", "Date Of Joining", "Create Date", "Update Date", "email Id", "Mobile Number"); 
        System.out.println("------------------------------------------------------------------------------------"
            +"---------------------------------------------------------------------------------------");  
        for(EmployeeDto employeeDto : employeeDtos) {
            System.out.println(employeeDto);
        }  
    }

    public void displayAllProjects(List<ProjectDto> projectDtos) {
        for(ProjectDto projectDto : projectDtos) {
            System.out.println(projectDto);
        }
    }
        
    public void displayEmployeesByRole(String userRole) throws CustomException {
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
        if (employeeService.checkEmployeeById(employeeId)) {
            signIn("employee", "update", employeeId);
        } else {
            System.out.println("\n not available \n");

        }       
        
    }

    public void updateProjects() throws CustomException {
        System.out.println("Enter the project Id you want to update");
        int projectId = scanner.nextInt();
        if (projectService.checkIsProjectAvailableById(projectId)) {
            addOrUpdateProject(Constants.PROJECT_MANAGER, "update", 0);
        } else {
            System.out.println("\n Not available \n");
        }
    }
   
    public void deleteEmployee() throws CustomException{
        System.out.println("Enter the employee Id you want to delete");
        int employeeId = scanner.nextInt();
        if (employeeService.checkEmployeeById(employeeId)) {
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
                //employeeService.updateEmployeeDetail(employeeId, gettingUpdatedValue("First Name"), "first_name");
                break;
        }
    }

    public String gettingUpdatedValue(String value) {
        System.out.println("Enter your" + value + ":");
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
            //projectService.assignProjectToEmployee(employeeId, projectId, assignDate, completionDate);
            System.out.println("Do you want to continue (y/n)");
            Scanner scanner1 = new Scanner(System.in);
            String userOption = scanner1.next();
            if(userOption.equals("n")) {
                isContinue = false;
            }
        }
    }
                
    public int validateAndGetProjectId() {
        boolean isValidProjectId = true;
        int projectId;
        Scanner scanner1 = new Scanner(System.in);
        while(isValidProjectId) {
            System.out.println("Enter the project Id");
            /*try {
                projectId = scanner1.nextInt();
                if(projectService.checkProjectById(projectId)) {
                    isValidProjectId = false;
                    return projectId;
                } else {
                    System.out.println("Enter correct project Id");
                } 
            } catch (CustomException customException) {
                System.out.println("You gave wrong project Id please give correct input");
            }*/

        }
        return 0;
    }
  
    public int validateAndGetEmployeeId() {
        boolean isValidEmployeeId = true;
        int employeeId;
        while (isValidEmployeeId) {
            System.out.println("Enter EmployeeId want to assign");
            try {
                employeeId = scanner.nextInt();
                if(employeeService.checkEmployeeById(employeeId)) {
                    isValidEmployeeId = false;
                    return employeeId;
                }
            } catch(CustomException customException) {
                System.out.println("You gave wroing input please give correct input");
                validateAndGetEmployeeId();
            }

        }
        return 0;
    }

    public String validateAndGetBothAssignedAndCompletionDate(String modeOfValidation) {
        boolean isValidDate = true;
        String date;
        while(isValidDate) {
            if(modeOfValidation == "assign") {
                System.out.println("Enter the date of assigning (yyyy-MM-dd)");
                date = scanner.next();
                String assignDate = DateUtil.validateAssignDateAndCompletionDate(date, "assign");
                if(assignDate == "Not valid") {
                    System.out.println("Invalid date");
                    validateAndGetBothAssignedAndCompletionDate("assign");
                } else {
                    return date;
                }
            } else {
                System.out.println("Enter the date of estimated completion date (yyyy-MM-dd)");
                date = scanner.next();
                String completionDate = DateUtil.validateAssignDateAndCompletionDate(date, "completion");
                if(completionDate == "Not valid") {
                    System.out.println("Invalid date");
                    validateAndGetBothAssignedAndCompletionDate("completion");
                } else {
                    return date;
                }
            }
        }     
        return "";       
    }


/*    public void displayAssignedProjects(List<EmployeeProjectDto> employeeProjectDtos) {
        for(EmployeeProjectDto employeeProjectDto : employeeProjectDtos) {
            System.out.println(employeeProjectDto);
            System.out.println(employeeProjectDto.getProjectName());
        }
    }   */ 
}