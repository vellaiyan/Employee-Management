/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import com.ideas2it.exception.CustomException;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * The {@code BaseDao} implemented to create a session factory.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 */
public class BaseDao {
    protected static SessionFactory sessionFactory = null;

    private BaseDao() {

    } 

    public static SessionFactory databaseConnection() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } 
      
        return sessionFactory;
    }    
}