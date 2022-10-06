/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import com.ideas2it.exception.CustomException;
import com.ideas2it.utilitis.Constants;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The {@code BaseDao} class implemented to connect the SQL database using username and password.
 * 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 */

public class BaseDao {
    protected static Connection connection = null;

    public static Connection databaseConnection() {

        if (connection = null) {
            connection = DriverManager.getConnection(Constants.DATABASE_URL,Constants.USER_NAME, Constants.PASSWORD);
        }	

        return connection;
    }
}



