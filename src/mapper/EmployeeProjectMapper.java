/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.mapper;

import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.model.EmployeeProject;

public class EmployeeProjectMapper {

    /**
     * {@code fromDto} to convert employeeProjectDto into employeeProject
     *
     * @param employeeProjectDto
     *       EmployeeProjectDto to be converted into EmployeeProject.
     *
     * @return employeeProject
     *
     * @since 1.0
     * 
     */ 
    public EmployeeProject fromDto(EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getEmployeeId(), employeeProjectDto.getProjectId(),             employeeProjectDto.getProjectName(), employeeProjectDto.getAssignedOn(), employeeProjectDto.getCompletedOn(), 
            employeeProjectDto.getRelievedOn(), employeeProjectDto.getStatus());
    return employeeProject;
    }

    /**
     * {@code fromDto} to convert employeeProject into employeeProjectDto.
     *
     * @param employeeProject
     *       EmployeeProejct to be converted into employeeProjectDto.
     *
     * @return employeeProjectDto.
     *
     * @since 1.0
     * 
     */ 
    public EmployeeProjectDto toDto(EmployeeProject employeeProject) {
        EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getEmployeeId(), employeeProject.getProjectId(),                         employeeProject.getProjectName(), employeeProject.getAssignedOn(), employeeProject.getCompletedOn(), 
            employeeProject.getRelievedOn(), employeeProject.getStatus());
        return employeeProjectDto;
    }
    
}

