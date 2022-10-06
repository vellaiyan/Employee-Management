/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The {@code RoleDao} class implemented to insert, retrive, update, delete all employees roles.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * 
 */
public class RoleDao {
    Connection connection = BaseDao.databaseConnection();
     
    /**
     * {@code assignEmployeeRole} to employee to the role.
     *
     * @param employeeId
     *       Id of the employee to be assign.
     *
     * @throws CustomException.
     *
     * @return assignedStatus.
     *
     * @since 1.1
     * 
     */
    public boolean assignEmployeeRole(int employeeId, int roleId) throws CustomException {
        try {
            String query = "insert into employee_roles(employee_id, role_id)" + " values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, roleId);
            preparedStatement.execute();
            return true;
        } catch (Exception exception) {
            throw new CustomException("Error occured while assign employee role", exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }   
    
    /**
     * {@code retrieveRoleByName} to retrieve roles by role name.
     *
     * @param roleName
     *       Role name to be retrieve.
     *
     * @throws CustomException.
     *
     * @return roleId
     *
     * @since 1.1
     * 
     */
    public int retriveRoleIdByName(String roleName) throws CustomException {
        int roleId;
        try {
            String sql = "select id from role where name = '"+ name + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()) {
                roleId = resultSet.getInt("id");
                return roleId;
            }
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieve role id by role name", exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        return 0;
    }
}