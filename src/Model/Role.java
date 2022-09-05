
package com.ideas2it.model;

import com.ideas2it.model.Employee;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Role {
    protected int roleId;
    protected String roleName;
    protected List<Employee> employees = new ArrayList<Employee>();

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}