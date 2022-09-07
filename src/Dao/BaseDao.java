/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import com.ideas2it.exception.CustomException;
import com.ideas2it.utilitis.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Session; 
import org.hibernate.Transaction;

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
    protected static SessionFactory factory =  new Configuration().configure().buildSessionFactory();
    public static Connection databaseConnection() {
        try {
            Connection connection = DriverManager.getConnection(Constants.DATABASE_URL,Constants.USER_NAME, Constants.PASSWORD);	
            return connection;
        } catch(Exception e) {
            System.out.println("Connection Failed"); 
            return null;           
        }  
    }
}



