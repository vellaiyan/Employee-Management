/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
 
package com.ideas2it.dao;

import com.ideas2it.model.Employee;
import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Role;
 
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
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.SessionFactory;
import org.hibernate.Session; 
import org.hibernate.Transaction;

/**
 * The {@code EmployeeDao} class implemented to insert, retrive, update, delete all employees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Retrive empoyee by EmployeeId.
 */

public class EmployeeDao extends BaseDao {

    public int insertEmployee(Employee employee) throws CustomException {
        SessionFactory factory = databaseConnection();
        Transaction transaction = null;
        Session session = null;
        int employeeId = 0;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            transaction.commit();
            return employeeId;
        } catch (Exception exception) {
            throw new CustomException("Error occured while inserting employee", exception);
        } finally {
            if (transaction != null || session != null) {
                session.close();
            }
        }
    }
 
    public List<Employee> retriveEmployees() throws CustomException {
        SessionFactory factory = databaseConnection();
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            transaction.commit();
            Query query = session.createQuery("FROM Employee where status = :status");  
            query.setString("status", "active");
            return query.getResultList();
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieving employees", exception);
        } finally {
            if (transaction !=null || session != null) {
                session.close();
            }
        }     
    }

    public boolean deleteEmployee(Employee employee) throws CustomException {
        SessionFactory factory = databaseConnection();
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return true;
        } catch (Exception exception) {
            throw new CustomException("Error occured while deleting employee", exception);
        } finally {
            if (session != null || transaction != null) {
                session.close();
            }
        }
    }  
    
    public boolean updateEmployeeDetails(Employee employee) throws CustomException {
        SessionFactory factory = databaseConnection();
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            session.update(employee);
            transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (Exception exception) {
            throw new CustomException("Error occured while updating employee details", exception);
        } finally {
            if (session != null || transaction != null) {
                session.close();
            }
        }
    }

    public Employee retriveEmployeeById(int employeeId) throws CustomException {
        SessionFactory factory = databaseConnection();
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.createQuery("FROM Employee where id = :id AND status = :status").setInteger("id", employeeId).setString  
                ("status","active").uniqueResult();;         
            transaction.commit();
            return employee;       
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieve employee by employee Id", exception);
        } finally {
             if (session != null || transaction != null) {
                 session.close();
             }
        }
    }
}





















