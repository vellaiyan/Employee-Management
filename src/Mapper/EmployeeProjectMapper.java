package com.ideas2it.mapper;

import com.ideas2it.dto.EmployeeProjectDto;
import com.ideas2it.model.EmployeeProject;

public class EmployeeProjectMapper {

    public EmployeeProject fromDto(EmployeeProjectDto employeeProjectDto) {
        EmployeeProject employeeProject = new EmployeeProject(employeeProjectDto.getProjectId(), employeeProjectDto.getEmployeeId(),                                     employeeProjectDto.getAssignedOn(), employeeProjectDto.getCompletedOn(), employeeProjectDto.getRelievedOn(),
            employeeProjectDto.getStatus());
        
        return employeeProject;

    }

    public EmployeeProjectDto toDto(EmployeeProject employeeProject) {
        EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto(employeeProject.getProject().getProjectId(),            
            employeeProject.getEmployee().getEmployeeId(), employeeProject.getAssignedOn(), employeeProject.getCompletedOn(),
            employeeProject.getRelievedOn(), employeeProject.getStatus());
        
        return employeeProjectDto;
    }
}