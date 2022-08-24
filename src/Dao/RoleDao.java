/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleDao extends BaseDao {
    Statement statement;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    int roleId;
     
    public void assignEmployeeRole(int employeeId, String employeeType) {
        try {
            connection = databaseConnection();
            System.out.println("entered");
            String sql = "select id from role where name = '"+ employeeType + "'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
            String query = " insert into employee_roles(employee_id, role_id)" + " values (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, roleId);
            preparedStatement.execute();

        } catch (Exception e) {

        }
    }   
}