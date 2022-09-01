/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.model.Project;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.mapper.EmployeeProjectMapper;
import com.ideas2it.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * The {@code ProjcetService} class implemented to support add, get, delete, projects to Project Manager.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */

public class ProjectService {
    private ProjectDao projectDao = new ProjectDao();
    private ProjectMapper projectMapper = new ProjectMapper();
    private EmployeeProjectMapper employeeProjectMapper = new EmployeeProjectMapper();
    private EmployeeProjectDao employeeProjectDao = new EmployeeProjectDao();

    public int addProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromDto(projectDto);
        return projectDao.insertProject(project);
    }

    public void assignProjectToEmployee(int employeeId, int projectId, LocalDate assignDate, LocalDate completionDate) throws CustomException {
        employeeProjectDao.assignEmployeeProject(employeeId, projectId, assignDate, completionDate);
    }

    public boolean checkProjectById(int projectId) throws CustomException {
        for(Project project : projectDao.getAllProjectIds()) {
            System.out.println(project.getProjectId());
            if(project.getProjectId() == projectId) {
                return true;
            }
        }
        return false;
    }

    public List<EmployeeProjectDto> getAssignedEmployeesById(int projectId) {
        List<EmployeeProjectDto> projectDtos = new ArrayList<EmployeeProjectDto>();
        List<EmployeeProject> projects = employeeProjectDao.retriveAssignedEmployees(projectId);
        for(EmployeeProject employeeProjects : projects) {
            System.out.println (employeeProjects.getProjectName() + "-----------");
            EmployeeProjectDto projectDto = employeeProjectMapper.toDto(employeeProjects);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }
}