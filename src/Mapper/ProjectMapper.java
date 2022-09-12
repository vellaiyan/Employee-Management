/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.mapper;

import com.ideas2it.dto.ProjectDto;
import com.ideas2it.model.Project;

/**
 * The {@code ProjectMapper} class implemented to convert projectDto into project vice versa.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */

public class ProjectMapper {

    public Project fromDto(ProjectDto projectDto) {
        Project project = new Project(projectDto.getProjectId(), projectDto.getProjectName(), projectDto.getProjectDescription(), 
            projectDto.getClientName(), projectDto.getCompanyName(), projectDto.getStartingDate(), projectDto.getEstimatedEndingDate(),                                  projectDto.getStatus());

        return project;
    }

    public ProjectDto toDto(Project project) {
        ProjectDto projectDto = new ProjectDto(project.getProjectId(), project.getProjectName(), project.getProjectDescription(), project.getClientName(),             project.getCompanyName(), project.getStartingDate(), project.getEstimatedEndingDate(), project.getStatus());
        
        return projectDto;
    }
}