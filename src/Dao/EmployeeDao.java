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
    private Connection connection = databaseConnection();
    private Employee employee;

    public int insertEmployee(Employee employee) throws CustomException {
        Transaction transaction = null;
        Session session = null;
        Integer employeeId = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            transaction.commit();
            session.close();
        } catch(HibernateException hibernateException) {
            throw new CustomException(hibernateException.getMessage());
        } finally {
            if(transaction != null || session != null) {
                session.close();
            }
        }
        return employeeId;
    }
 
    public List<Employee> retriveEmployees() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            transaction.commit();
            return session.createQuery("FROM Employee where status = 'active'").getResultList();  
        } finally {
            if(transaction !=null || session != null) {
                session.close();
            }
        }     
    }

    public Role retriveEmployeeByRole(String role) throws CustomException {
        Session session = null;
        try {
            session = factory.openSession();
            Criteria criteria = session.createCriteria(Role.class);
            criteria.add(Restrictions.eq("name", role));
            List<Role> roles = criteria.list();
            return roles.get(0);            
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public boolean deleteEmployeeById(int employeeId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query query  = session.createQuery("update Employee set status = 'inactive' where id = " + employeeId);         
            query.executeUpdate();
            transaction.commit();
            return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }  
    
    public boolean updateEmployeeDetailsById(Employee employee) throws CustomException {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            session.update(employee);
            transaction = session.beginTransaction();
            transaction.commit();
            session.close();
            return true;
        } catch(HibernateException hibernateException) {
            throw new CustomException(hibernateException.getMessage());
        }
    }

    public boolean updateEmployeeDetailById(int employeeId, String value, String fieldName) throws CustomException {
        try {
            PreparedStatement preparedStatement;
            Date date = new Date(0);
            String query = "update employee set " + fieldName + " = ?, modified_date = current_timestamp where id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException sqlException) {
            throw new CustomException(sqlException.getMessage());
        }
    }
  
    public Employee retriveEmployeeById(int employeeId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("FROM Employee where id = " + employeeId).getResultList();         
            transaction.commit();
            return employees.get(0);       
        } finally {
             if(session != null || transaction !=null) {
                 session.close();
             }
        }
    }
}





















