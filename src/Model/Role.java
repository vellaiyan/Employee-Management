
package com.ideas2it.model;

import com.ideas2it.model.Employee;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;  
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {    
    @Id
    @Column(name = "id")
    protected int roleId;
    
    @Column(name = "name")
    protected String name;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "roles")
    protected List<Employee> employees = new ArrayList<Employee>();

    public Role(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
 
    public Role() {
  
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Role> defaultRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role(1,"Trainee"));
        roles.add(new Role(2, "Trainer"));
        roles.add(new Role(3, "Project Manager"));
        roles.add(new Role(4, "Human Resource"));
        return roles;
    }   
 
}