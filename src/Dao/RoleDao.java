/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The {@code RoleDao} class implemented to insert, retrive, update, delete all employees roles.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */

public class RoleDao extends BaseDao {
    Connection connection = databaseConnection();
     
    public boolean assignEmployeeRole(int employeeId, int roleId) {
        try {
            String query = "insert into employee_roles(employee_id, role_id)" + " values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, roleId);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }   
    
    public int retriveRoleIdByName(String name) {
        int roleId;
        try {
            String sql = "select id from role where name = '"+ name + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("role dao working fine");
                roleId = resultSet.getInt("id");
                return roleId;
            }
        } catch (Exception e) {

        }
        return 0;
    }
}