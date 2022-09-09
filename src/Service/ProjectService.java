/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.service;

import com.ideas2it.dao.ProjectDao;
import com.ideas2it.dto.ProjectDto;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.exception.CustomException;
import com.ideas2it.mapper.ProjectMapper;
import com.ideas2it.model.Project;

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
    ProjectMapper projectMapper = new ProjectMapper();
    ProjectDao projectDao = new ProjectDao();

    public boolean addProject(ProjectDto projectDto) throws CustomException {
        Project project = projectMapper.fromDto(projectDto);
        int projectId = projectDao.insertProject(project);

        return true;
    }

    public List<ProjectDto> getProjects () throws CustomException {
        List<Project> projects = projectDao.retrieveProjects();
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        for (Project project : projects) {
            ProjectDto projectDto = projectMapper.toDto(project);
            projectDtos.add(projectDto);
        }

        return projectDtos;
    }
        
    public boolean checkIsProjectAvailableById(int projectId) throws CustomException {
        for (Project project : projectDao.retrieveProjects()) {
            if (project.getProjectId() == projectId) {

                return true;
            }
        }
        return false;
    }

    public ProjectDto getProjectById(int projectId) throws CustomException {
        return projectMapper.toDto(projectDao.retrieveProjectById(projectId));
    }

    public boolean updateProject(ProjectDto projectDto, int projectId) throws CustomException {        
        Project project = projectMapper.fromDto(projectDto);
        project.setProjectId(projectId);

        return projectDao.updateProject(project);
    }

    public boolean deleteProject(int projectId) throws CustomException {
        ProjectDto projectDto = getProjectById(projectId);
        System.out.println(projectDto);
        Project project = projectMapper.fromDto(projectDto);
        List<EmployeeProject> employeeProject = new ArrayList<EmployeeProject>();
        project.setEmployeeProjects(employeeProject);
        project.setStatus("inactive");
        System.out.println(project.getStatus());
        
        return projectDao.deleteProject(project);
    }
}