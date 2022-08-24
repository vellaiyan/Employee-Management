/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import com.ideas2it.utilitis.Constants;
import com.ideas2it.exception.CustomException;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
    public Connection databaseConnection() throws CustomException {
        try {
            Connection connection = DriverManager.getConnection(Constants.DATABASE_URL,Constants.USER_NAME, Constants.PASSWORD);	
            return connection;
        } catch(Exception e) {
            throw new CustomException("Connection Failed");            
        }  
    }
}