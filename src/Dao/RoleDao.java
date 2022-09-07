/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.dao;


import com.ideas2it.model.Role;
import com.ideas2it.exception.CustomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;  
import java.text.SimpleDateFormat; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;  
import java.sql.Date;
import java.sql.SQLException;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.SessionFactory;
import org.hibernate.Session; 
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

public class RoleDao extends BaseDao {
    Connection connection = databaseConnection();
    Role role = new Role();

    public boolean insertRoles() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Role> roles = role.defaultRoles();
        try { 
            for(Role role : roles) {
                
                session.save(role); 
            }
            transaction.commit(); 
            session.close(); 
            return true; 
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
   
    public List<Role> retrieveRoleByName(String name) throws CustomException {
        try {
            Session session = factory.openSession(); 
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", name));
            return criteria.list();
        } catch(HibernateException hibernateException) { 
            throw new CustomException(hibernateException); 
        }   
    }

}