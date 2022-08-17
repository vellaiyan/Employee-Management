/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.model;

public class HumanResource {
    protected String employeeId;
     
    public HumanResource(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
	return employeeId;
    }

    @Override  
    public String toString() {
        return  "\nEmployee Id      : " + employeeId;
    }
}