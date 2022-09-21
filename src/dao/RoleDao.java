/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;

import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Role;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;  
import java.text.SimpleDateFormat; 

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;  

import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    SessionFactory factory = BaseDao.databaseConnection();

    /**
     * {@code insertRoles} to insert the default roles.
     *
     * @throws CustomException.
     *
     * @return insertedStatus.
     *
     * @since 1.1
     * 
     */ 
    public boolean insertRoles() throws CustomException {
        Transaction transaction = null;
        Session session = null;
        Role roleObject = new Role();
        List<Role> roles = roleObject.defaultRoles();
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            for (Role role: roles) {                
                session.save(role); 
            }        
            transaction.commit(); 
            return true; 
        } catch (Exception exception) {
            throw new CustomException("Error while insert roles", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
   
    /**
     * {@code retrieveRolesByRoleName} to retrieve roles by role name.
     *
     * @param roleName
     *       Role name to be retrieve.
     *
     * @throws CustomException.
     *
     * @return roles.
     *
     * @since 1.1
     * 
     */ 
    public List<Role> retrieveRolesByRoleName(String roleName) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        
        try {
            session = factory.openSession(); 
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", roleName));

            return criteria.list();
        } catch (HibernateException hibernateException) { 
            throw new CustomException("Error while retrieve roles by role name", hibernateException); 
        } finally {
            if (session != null) {
                session.close();
            }
        } 
    }

    /**
     * {@code retrieveRoleByRoleName} to retrieve role by role name.
     *
     * @param roleName
     *       Role name to be retrieve.
     *
     * @throws CustomException.
     *
     * @return role.
     *
     * @since 1.1
     * 
     */ 
    public Role retriveRoleByRoleName(String roleName) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            transaction.commit();
            Role role = (Role)session.createQuery("FROM Role where name = :name").setString("name", roleName).uniqueResult(); 

            return role;          
        } catch (HibernateException hibernateException) {
            throw new CustomException("Error while retrieve role by role name", hibernateException);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}