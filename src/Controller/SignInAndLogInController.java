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
 * The {@code SignInAndLogInController} class helps all employees to signin or login into their account. 
 * This class also provides modification support aswell. 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Showing details in table format.
 */

public class SignInAndLogInController { 
    private EmployeeService employeeService = new EmployeeService();
    private ProjectService projectService = new ProjectService();
    
    /**
     * {@code signIn} implemented to sign-in  for all employees.
     * 
     * @param userRole
     *       Decides which user is going to sign-in (Trainee/Trainer/Project manager/Human Resource).
     *
     * @since 1.0
     *
     */
    public void signIn(String userRole, String processToBeProceed, int employeeId) throws CustomException {
        EmployeeDto employeeDto = new EmployeeDto();   
        DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime localDate = LocalDateTime.now();     
        employeeDto.setFirstName(getAndValidateInputByRegex("First Name", ValidationUtil.NAME_PATTERN));
        employeeDto.setSubject(getAndValidateInputByRegex("Subject", ValidationUtil.NAME_PATTERN));
        employeeDto.setDateOfBirth(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter your date of birth (yyy-MM-dd)", "dob")));
        employeeDto.setDateOfJoining(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter your date of joining (yyyy-MM-dd)", "joining")));
        employeeDto.setBatch(Integer.parseInt((getAndValidateInputByRegex("batch Number", ValidationUtil.EMPLOYEE_ID_PATTERN))));
        employeeDto.setGender(getAndValidateInputByRegex("Gender", ValidationUtil.GENDER_PATTERN));
        employeeDto.setEmailId(getAndValidateInputByRegex("Email-Id", ValidationUtil.EMAIL_PATTERN));
        employeeDto.setMobileNumber(Long.parseLong(getAndValidateInputByRegex("Mobile Number", ValidationUtil.PHONE_PATTERN)));        
        employeeDto.setStatus(Constants.ACTIVE_STATUS);

        if (processToBeProceed.equals("add")) {
            addEmployee(employeeDto, localDate, userRole);                         
        } else if (processToBeProceed.equals("update")) {
            employeeDto.setUpdateDate(localDate);
            boolean isUpdated = employeeService.updateEmployeeDetails(employeeDto, employeeId, userRole);
        } else {
            assignRoleToEmployee(employeeDto, employeeId);
        }
    }

    /**
     * {@code getDateOfBirthAndDateOfJoining} is common method to get and validate the date of birth and date of joining from the user.
     *
     * @param informationToPrint
     *       For which information to ask while getting the input from the user.
     *
     * @param choosenDate
     *       For which date (date of birth / joining date) is going to validate.
     *
     * @since 1.0
     * 
     */

    public String  getDateOfBirthAndDateOfJoining(String informationToPrint, String choosenDate) throws CustomException {
        Scanner scanner = new Scanner(System.in);
        boolean isValidDate = true;
        String dateOfBirthAndJoining = "";
        int remainingTimes = 0;
        for (int checkingLoop = 0; checkingLoop < 5; checkingLoop++) {            
            EmployeeController.logger.info("informationToPrint");
            dateOfBirthAndJoining = scanner.next();
            String date = DateUtil.validateDateOfBirth(dateOfBirthAndJoining, choosenDate);

            if (date.equals(dateOfBirthAndJoining)) {
                isValidDate = false;
                checkingLoop = 4;
                dateOfBirthAndJoining = date;
            } else if (date.equals("max")) {
                EmployeeController.logger.info("Your date of birth exceeds current year");
                remainingTimes++;
            } else if (date.equals("low")) { 
                EmployeeController.logger.error("Given date of birth is not valid");
                remainingTimes++;
            } else if (date.equals("min")) {
                EmployeeController.logger.error("Your age is too low");
                remainingTimes++;
            } else if (date.equals("invalid")) {
                EmployeeController.logger.error("Invalid dateOfBirth");
                remainingTimes++;
            } if (remainingTimes == 5) {
                throw new CustomException("Invalid Input");                
            }
        }
        return dateOfBirthAndJoining;
    }   
  

    /**
     * {@code getAndValidateInputByRegex} is the common method to get and validate the all inputs.
     *
     * @param informationToPrint
     *       For which information to ask while getting the input from the user.
     *
     * @param regex
     *       Regex to be checked with user input.      
     *
     * @since 1.0
     * 
     */
    private String getAndValidateInputByRegex(String informationToPrint, String regex) throws CustomException{
        Scanner scanner = new Scanner(System.in);
        String initialUserInput = "";
        int remainingTimes = 0;
        for (int numberOfTimes = 0; numberOfTimes < 5; numberOfTimes++) {
                EmployeeController.logger.info("Enter Your " + informationToPrint + " : ");
                String userInput = scanner.next();
                boolean isvalid = ValidationUtil.inputCheckingByRegex(regex, userInput);
                if (isvalid) {
                    initialUserInput = userInput;
                    numberOfTimes = 4;               
                } else if (!isvalid) {
                    EmployeeController.logger.error("Invalid " + informationToPrint + "("+(5-(numberOfTimes+1)) + " more times)");
                    remainingTimes++;                                 
                } if (remainingTimes == 5){
                    throw new CustomException("Invalid");
                }
        }
        return initialUserInput;
    }   


    /**
     * {@code addOrUpdateProject} is the common method for add and update the project details.
     *
     * @param userRole
     *       which user is going to add or update project.
     *
     * @param projecessToBeProceed
     *       which proecss (add or update) is going to perform.
     *
     * @param projectId
     *       Project id required to update project.
     *
     * @since 1.0
     * 
     */    
    public void addAndUpdateProject(String userRole, String processToBeProceed, int projectId) throws CustomException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(0);
        projectDto.setProjectName(getAndValidateInputByRegex("Project Name", ValidationUtil.NAME_PATTERN));
        projectDto.setProjectDescription(getAndValidateInputByRegex("Project Description", ValidationUtil.NAME_PATTERN));
        projectDto.setClientName(getAndValidateInputByRegex("Client Name", ValidationUtil.NAME_PATTERN));
        projectDto.setCompanyName(getAndValidateInputByRegex("Company Name", ValidationUtil.NAME_PATTERN));
        projectDto.setStartingDate(LocalDate.parse(getDateOfBirthAndDateOfJoining("Enter project starting date", "dob")));
        projectDto.setEstimatedEndingDate(LocalDate.parse(getDateOfBirthAndDateOfJoining("Estimated ending date", "joining"))); 
        projectDto.setStatus(Constants.ACTIVE_STATUS);
    
        if (processToBeProceed.equals("add")) {
            projectService.addProject(projectDto);
        } else {
            projectService.updateProject(projectDto, projectId);
        }
        
    }

    /**
     * {@code trainerOperations} is only for trainers to perform trainee related CURD operations.
     *
     * @since 1.0
     * 
     */ 
    public void traineeOperations() throws CustomException {
        Scanner scanner = new Scanner(System.in);
        EmployeeController.logger.info("Enter the user name : ");
        String userName = scanner.next();
        EmployeeController.logger.info("Enter the password : ");
        String password = scanner.next();
        boolean isContinue = true;
        if ((userName.equals("ideas2it")) && (password.equals("admin"))) {
            EmployeeController.logger.info("1. View all trainee details. \n2. View all trainer details. \n3. View all project managers."
                +"\n4. Update employee details. \n5. Delete employee \n6. Update employee particular detail. \n7. View particular employee"
                + "\n8. Display all employees. \n9. Display all projects. \n10. Exit");
            String userOption = scanner.next();
                switch (userOption) {
                    case "1":
                        displayEmployeesByRole(Constants.TRAINEE);
                        break;
 
                    case "2":
                        displayEmployeesByRole(Constants.TRAINER);
                        break;

                    case "3":
                        displayEmployeesByRole(Constants.PROJECT_MANAGER);
                        break;

                    case "4":
                         updateEmployeeDetails();
                         break;
                    
                    case "5":
                        validateAndDeleteEmployee();
                        break;

                    case "6":
                        validateAndUpdateEmployeeDetail();
                        break;

                    case "7":
                        validateAndDisplayEmployeeDetail();
                        break;

                    case "8":
                        displayAllEmployees(employeeService.getEmployees());
                        break;
   
                    case "9" :
                        displayAllProjects(projectService.getProjects());
                        break;
                    
                    default:
                        isContinue = false;
                }        
        }
    } 

    /**
     * {@code displayAllEmployees} is implemented to display all the active employees.
     *
     * @param employeeDtos
     *       List of employeeDtos to display.
     *
     * @since 1.0
     * 
     */ 
    public void displayAllEmployees(List<EmployeeDto> employeeDtos) {
        for (EmployeeDto employeeDto: employeeDtos) {
            EmployeeController.logger.info(employeeDto);
        } 
    }

    /**
     * {@code displayAllProjects} implemented to print all the projects 
     *
     * @param projectDtos
     *       List of projectDtos to display.
     *
     * @since 1.0
     * 
     */ 
    public void displayAllProjects(List<ProjectDto> projectDtos) {
        for (ProjectDto projectDto: projectDtos) {
            EmployeeController.logger.info(projectDto);
        }
    }
        
    /**
     * {@code displayEmployeesByRole} is implemented to display all the employees based on the role(Trainer/Trainee/Project Manager/Human Resource).
     *
     * @param userRole
     *       To display all employees.
     *
     * @param projectId
     *       Project id required to update project.
     *
     * @since 1.0
     * 
     */ 
    public void displayEmployeesByRole(String userRole) throws CustomException {
        try { 
            List<EmployeeDto> employeeDtos = employeeService.getEmployeesByRole(userRole);
            for (EmployeeDto employeeDto: employeeDtos) {
                EmployeeController.logger.info(employeeDto);
            }  
        } catch (CustomException exception) {
            EmployeeController.logger.error(exception);
        }
    }

    /**
     * {@code validateAndDisplayEmployeeDetail} implemented to validate and display employee detail.
     *
     * @since 1.0
     * 
     */ 
    public void validateAndDisplayEmployeeDetail() {
        try {
            Scanner scanner = new Scanner(System.in);
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
    }

    /**
     * {@code updateEmployeeDetails} uses employee to update their details.
     *
     * @since 1.0
     * 
     */ 
    public void updateEmployeeDetails() {
        try {
            Scanner scanner = new Scanner(System.in);
            EmployeeController.logger.info("Enter the Employee Id you want to update");
            int employeeId = scanner.nextInt();
            if (employeeService.checkEmployeeById(employeeId)) {
                signIn("employee", "update", employeeId);
            } else {
                EmployeeController.logger.info("\n not available \n");
            }
        } catch (CustomException exception) {
            EmployeeController.logger.error(exception);
        }
    }

    /**
     * {@code ValidateAndUpdateProject} is used to validate and update the project.
     *
     * @throws CustomException
     *
     * @since 1.0
     * 
     */ 
    public void validateAndUpdateProject() throws CustomException {
        Scanner scanner = new Scanner(System.in);
        EmployeeController.logger.info("Enter the project Id you want to update");
        int projectId = scanner.nextInt();
        if (projectService.checkIsProjectAvailableById(projectId)) {
            addAndUpdateProject(Constants.PROJECT_MANAGER, "update", 0);
        } else {
            EmployeeController.logger.info("\n Not available \n");
        }
    }
   
    /**
     * {@code validateAndDeleteEmployee} is implemented to validate and delete the employee id he/she exist.
     *
     * @since 1.0
     * 
     */ 
    public void validateAndDeleteEmployee() {
        try {
            Scanner scanner = new Scanner(System.in);
            EmployeeController.logger.info("Enter the employee Id you want to delete");
            int employeeId = scanner.nextInt();
            if (employeeService.checkEmployeeById(employeeId)) {
                boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
                EmployeeController.logger.info("Employee deleted");
            } else {
                EmployeeController.logger.error("Not available\n");
            }
        } catch (CustomException exception) {
            EmployeeController.logger.error(exception);
        } 
    }

    /**
     * {@code updateEmployeeDetail} is implemented to update the employee detail.
     *
     * @since 1.0
     * 
     */ 
    public void validateAndUpdateEmployeeDetail() {
        try {
            Scanner scanner = new Scanner(System.in);
            EmployeeController.logger.info("Enter the user email id you want to update");
            int employeeId= scanner.nextInt();
            if (!employeeService.checkEmployeeById(employeeId)) {
                EmployeeController.logger.error("Invalid email Id");
            } else {
                EmployeeController.logger.info("Choose what details you want to change.\n1. Batch.\n2. First Name. \n3. Subject. \n4. Gender."
                    + "\n5. Date of birth. \n6. Joining date. \n7. Email Id. \n8. Mobile Number.");
                int employeeChoice = scanner.nextInt();
            }
        
        } catch (InputMismatchException inputMismatchException) {
            EmployeeController.logger.error(inputMismatchException);
        } catch (CustomException customException) {
            EmployeeController.logger.error(customException);
        }
    }

    /**
     * {@code getUpdatedValue) is the common method to get all the updated values.
     *
     * @param informationToPrint
     *       which update value is going to get from user.
     *
     * @since 1.0
     * 
     */ 
    public String getUpdatedValue(String informationToPrint) {
        Scanner scanner = new Scanner(System.in);
        EmployeeController.logger.info("Enter your" + informationToPrint + ":");
        String updatedInput = scanner.next();
        return updatedInput;
    }

    /**
     * {@code assignEmployees} is implemented to assign the employees for the projects.
     *
     * @since 1.0
     * 
     */ 
    public void assignEmployees() {
        try {
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
                employeeProjectDto.setStatus(Constants.ACTIVE_STATUS);
            
                projectService.assignProjectToEmployee(employeeProjectDto);
                EmployeeController.logger.info("Do you want to continue (y/n)");
                Scanner scanner = new Scanner(System.in);
                String userOption = scanner.next();

                if (userOption.equals("n")) {
                    isContinue = false;
                }
            }
        } catch (CustomException exception) {
            EmployeeController.logger.error(exception);
        }
    }
   
    /**
     * {@code validateAndGetProjectId} is implemented to validate and get the project id from the employee.
     *
     * @since 1.0
     * 
     */              
    public int validateAndGetProjectId() {
        boolean isValidProjectId = true;
        Scanner scanner = new Scanner(System.in);
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

    /**
     * {@code validateAndGetEmployeeId} is implemented to validate and get the employee id.
     *
     * @since 1.0
     * 
     */ 
  
    public int validateAndGetEmployeeId() {
        boolean isValidEmployeeId = true;
        Scanner scanner = new Scanner(System.in);
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

    /**
     * {@code validateAndGetBothAssignedAndCompletionDate} is implemented to validate and get both assigned and completion date from the employee.
     *
     * @param modeOfValidation
     *       which date (date of birth / date of joinint) is going to validate.
     *
     * @since 1.0
     * 
     */ 

    public String validateAndGetBothAssignedAndCompletionDate(String modeOfValidation) {
        boolean isValidDate = true;
        Scanner scanner = new Scanner(System.in);
        while (isValidDate) {
            if (modeOfValidation.equals("assign")) {
                EmployeeController.logger.info("Enter the date of assigning (yyyy-MM-dd)");
                String assignDate = scanner.next();
                String result = DateUtil.validateAssignDateAndCompletionDate(assignDate, "assign");
                if (result.equals("Not valid")) {
                    EmployeeController.logger.error("Invalid date");
                    validateAndGetBothAssignedAndCompletionDate("assign");
                } else {
                    return assignDate;
                }
            } else {
                EmployeeController.logger.info("Enter the date of estimated completion date (yyyy-MM-dd)");
                String completionDate = scanner.next();
                String result = DateUtil.validateAssignDateAndCompletionDate(completionDate, "completion");
                if (result.equals("Not valid")) {
                    EmployeeController.logger.error("Invalid date");
                    validateAndGetBothAssignedAndCompletionDate("completion");
                } else {
                    return completionDate;
                }
            }
        }    
 
        return "";       
    } 

    /**
     * {@code addEmployee} is implemented to add the employee.
     *
     * @param employeeDto
     *       EmployeeDto to add.
     *
     * @param localDate
     *       LocalDate for creating date.
     *
     * @param userRole
     *       User role to be add.
     *
     * @since 1.0
     * 
     */ 
    public void addEmployee(EmployeeDto employeeDto, LocalDateTime localDate, String userRole) throws CustomException {
        employeeDto.setCreateDate(localDate);
        employeeDto.setUpdateDate(localDate);
        boolean isAdded = employeeService.addEmployee(employeeDto, userRole);
       EmployeeController.logger.debug("\nYour details are added in Trainee list.\nYou have to LogIn again to access your account\n");
    }

    /**
     * {@code assignRoleToEmployee} is implemented to assigning the role to the employee.
     *
     * @param employeeDto
     *       EmployeeDto object to be assign.
     *
     * @param employeeid
     *       EmployeeId to be assign.
     *
     * @since 1.0
     * 
     */ 
    public void assignRoleToEmployee(EmployeeDto employeeDto, int employeeId) throws CustomException {
        Scanner scanner = new Scanner(System.in);
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
        employeeService.updateEmployeeDetails(employeeDto, employeeId, role);
    }

}