/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
 
package com.ideas2it.dao;

import com.ideas2it.exception.CustomException;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.model.Project;
import com.ideas2it.utils.Constants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;   
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException; 
import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * The {@code ProjectDao} class implemented to insert, retrive, update, delete all projects.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Retrive project by projectId.
 */
public class ProjectDao {  
    SessionFactory sessionFactory = BaseDao.databaseConnection();

    /**
     * {@code insertProject} to insert the new project.
     *
     * @param project
     *       project object to be insert.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */ 
    public int insertProject(Project project) throws CustomException {
        Transaction transaction = null;
        Session session = null;
        int projectId = 0;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            projectId = (Integer) session.save(project);
            transaction.commit();
            return projectId;
        } catch (Exception exception) {
            throw new CustomException("Error occured while inserting porject", exception);
        } finally {
            if (transaction != null) {
                session.close();
            }
        }
    }

    /**
     * {@code retrieveProjects} to retrieve all the projects.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */
    public List<Project> retrieveProjects() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try { 
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Project where status = :status");
            query.setString("status", "active");
            transaction.commit();
            return query.getResultList();
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieving Projects", exception);
        } finally {
            if (transaction != null) {
                session.close();
            }
        }
    }

    /**
     * {@code updateProject} to update project details.
     *
     * @param project
     *       project need to be update.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */ 
    public boolean updateProject(Project project) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            session.update(project);
            transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception);
            throw new CustomException("Error occured while updating project details", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }  
    }

    /**
     * {@code deleteProject} to delete the project
     *
     * @param project
     *       Project to be deleted.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */ 
    public boolean deleteProject(Project project) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            session.saveOrUpdate(project);
            transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (Exception exception) {
            throw new CustomException("Error occured while deleting project", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
 
    /**
     * {@code retrieveProjectById} to retrieve project based on the employee id.
     *
     * @param projectId
     *       project id to be retrieve.
     *
     * @throws CustomException.
     *
     * @since 1.1
     * 
     */ 
    public Project retrieveProjectById(int projectId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Project project = (Project) session.createQuery("FROM Project where id = :id AND status = :status")
                .setInteger("id", projectId).setString("status", "active").uniqueResult();
            transaction.commit();
            return project;
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieve project by Id", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * {@code insertAssignedProject} to insert the assigned project
     *
     * @param employeeProject
     *       EmployeeProject to be insert.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */ 
    public boolean insertAssignedProject(EmployeeProject employeeProject) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            session.save(employeeProject);
            transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (Exception exception) {
            throw new CustomException("Error occured while assigning project", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * {@code retrieveAllAssignedProjects} to retrieve  all the assigned projects.
     *
     * @throws CustomException.
     *
     * @since 1.0
     * 
     */ 
    public List<EmployeeProject> retrieveAllAssignedProjects() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.commit();
            Query query = session.createQuery("FROM EmployeeProject where status = :status");
            query.setString("status", Constants.ACTIVE_STATUS);
            return query.getResultList();
        } catch (Exception exception) {
            throw new CustomException("Error occured while retrieving all assigned projects", exception);
        } finally {
            if (transaction != null) {
                session.close();
            }
        }   
    }
}