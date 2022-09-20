/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.EmployeeProjectMapper;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.model.Project;
import com.ideas2it.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ProjcetService} class implemented to support add, get, delete, projects to Project Manager.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */
public class ProjectService {

   /**
     * {@code addProject} to add new project.
     *
     * @param projectDto
     *       ProjectDto object to be add in the database.
     *
     * @throws CustomException.
     *
     * @return addedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean addProject(ProjectDto projectDto) throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = projectMapper.fromDto(projectDto);
        int projectId = projectDao.insertProject(project);

        return true;
    }

    /**
     * {@code geProjects} to get all projects.
     *
     * @throws CustomException.
     *
     * @return projectDtos.
     *
     * @since 1.0
     * 
     */ 
    public List<ProjectDto> getProjects() throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        ProjectMapper projectMapper = new ProjectMapper();
        List<Project> projects = projectDao.retrieveProjects();
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        for (Project project: projects) {
            ProjectDto projectDto = projectMapper.toDto(project);
            projectDtos.add(projectDto);
        }

        return projectDtos;
    }
 
    /**
     * {@code checkIsProjectAvailableById} to check project is available or not by project id.
     *
     * @param projectId
     *       Project id need to be check.
     *
     * @throws CustomException.
     *
     * @return checkedStatus.
     *
     * @since 1.0
     * 
     */        
    public boolean checkIsProjectAvailableById(int projectId) throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        for (Project project: projectDao.retrieveProjects()) {
            if (project.getProjectId() == projectId) {

                return true;
            }
        }
        return false;
    }

    /**
     * {@code getProjectById} to get project based on the project id.
     *
     * @param projectId
     *       Project id need to be get.
     *
     * @throws CustomException.
     *
     * @return projectDto.
     *
     * @since 1.0
     * 
     */ 
    public ProjectDto getProjectById(int projectId) throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        ProjectMapper projectMapper = new ProjectMapper();
        return projectMapper.toDto(projectDao.retrieveProjectById(projectId));
    }


    /**
     * {@code updateProject} to update project all details.
     *
     * @param projectDto
     *       ProjectDto object to be update in the database.
     *
     * @ProjectId
     *       Project id to be update.
     *
     * @throws CustomException.
     *
     * @return updatedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean updateProject(ProjectDto projectDto, int projectId) throws CustomException {   
        ProjectDao projectDao = new ProjectDao(); 
        ProjectMapper projectMapper = new ProjectMapper();    
        Project project = projectMapper.fromDto(projectDto);
        project.setProjectId(projectId);

        return projectDao.updateProject(project);
    }

    /**
     * {@code deleteProjectById} to delete project by project Id.
     *
     * @param projectId
     *       Project id need to be delete.
     *
     * @throws CustomException.
     *
     * @return deleteStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean deleteProject(int projectId) throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        ProjectMapper projectMapper = new ProjectMapper();
        ProjectDto projectDto = getProjectById(projectId);
        projectDto.setStatus("inactive");
        Project project = projectMapper.fromDto(projectDto);
        List<EmployeeProject> employeeProject = new ArrayList<EmployeeProject>();
        project.setEmployeeProjects(employeeProject);
      
        return projectDao.deleteProject(project);
    }

    /**
     * {@code assignProjectToEmployee} to assign projects to the employees.
     *
     * @param employeeProjectDto
     *       EmployeeProjectDto to be assigned.
     *
     * @throws CustomException.
     *
     * @return assignedStatus.
     *
     * @since 1.0
     * 
     */ 
    public boolean assignProjectToEmployee(EmployeeProjectDto employeeProjectDto) throws CustomException {    
        EmployeeService employeeService = new EmployeeService();
        ProjectDao projectDao = new ProjectDao();
        EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
        ProjectMapper projectMapper = new ProjectMapper();
        EmployeeProject employeeProject = employeeProjectMapper.fromDto(employeeProjectDto);
        Employee employee = employeeService.getEmployeeDetailsById(employeeProjectDto.getEmployeeId());
        ProjectDto projectDto = getProjectById(employeeProjectDto.getProjectId());
        Project project = getProjectDetailsById(employeeProjectDto.getProjectId());
        employeeProject.setEmployee(employee);
        employeeProject.setProject(project);
        return projectDao.insertAssignedProject(employeeProject);
    }

    /**
     * {@code setUpdatedProject} to set updated project in the database.
     *
     * @param project
     *       Project need to be update.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */ 
    public void setUpdatedProject(Project project) throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        projectDao.updateProject(project);

    }
 
    /**
     * {@code getProjectDetailsById} To get project details by project id.
     *
     * @param projectId
     *       Project id need to be get.
     *
     * @throws CustomException.
     *
     * @return project.
     *
     * @since 1.0
     * 
     */ 
    public Project getProjectDetailsById(int projectId) throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        return projectDao.retrieveProjectById(projectId);
    }

    public List<EmployeeProjectDto> getAllAssignedProjects() throws CustomException {
        ProjectDao projectDao = new ProjectDao();
        EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
        ProjectMapper projectMapper = new ProjectMapper();
        List<EmployeeProjectDto> employeeProjectDtos = new ArrayList<EmployeeProjectDto>();
        for (EmployeeProject employeeProject: projectDao.retrieveAllAssignedProjects()) {
            EmployeeProjectDto employeeProjectDto = employeeProjectMapper.toDto(employeeProject);
            employeeProjectDtos.add(employeeProjectDto);
       }

        return employeeProjectDtos;
    }

    /**
     * {@code getAssignedEmployeesForSingleProject} to get all the assigned employees for the single project.
     *
     * @param projectId
     *       Project id to get all the assigned employees.
     *
     * @throws CustomException.
     *
     * @return List of employeeDtos.
     *
     * @since 1.0
     * 
     */ 
    public List<EmployeeDto> getAssignedEmployeesForSingleProject(int projectId) throws CustomException {
        Project project = getProjectDetailsById(projectId);
        EmployeeMapper employeeMapper = new EmployeeMapper();
        List<EmployeeProject> employeeProjects = project.getEmployeeProjects();
        List<Employee> employees = new ArrayList<Employee>();
 
        for (EmployeeProject employeeProject: employeeProjects) {
        ProjectDao projectDao = new ProjectDao();
            employees.add(employeeProject.getEmployee());
        }        
        List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
        for (Employee employee: employees) {
        ProjectDao projectDao = new ProjectDao();
            employeeDtos.add(employeeMapper.toDto(employee));
        }

        return employeeDtos;
    }
}