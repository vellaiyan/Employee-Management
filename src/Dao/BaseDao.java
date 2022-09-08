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
    static SessionFactory sessionFactory = null;

    public SessionFactory databaseConnection() throws CustomException {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        } catch (HibernateException hibernateException) {
            throw new CustomException("Error occured while creating session factory", hibernateException);
        }        
        return sessionFactory;
    }    
}



