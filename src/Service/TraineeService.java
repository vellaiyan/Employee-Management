/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.service;

import com.ideas2it.dao.TraineeDao;
import com.ideas2it.model.Trainee;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TraineeService} class implemented to support add, get, 
 * delete, functionalities to trainees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding default trainees.
 */
   
public class TraineeService {   
    private TraineeDao traineeDao = new TraineeDao();
   
   /**
     * {@code addTrainee} to pass new trainee to {@code TraineeDao}.
     *
     * @param firstName
     *           First name of trainee.
     *
     * @param lastName 
     *          Last name of trainee.
     *
     * @param id 
     *          Id of trainee.
     * 
     * @param batch
     *          Batch of the trainee.
     *
     * @param date 
     *          Date of birth of the trainee.
     *
     * @param fatherName 
     *          Father name of the trainee.
     *
     * @param motherName 
     *         Mother name of the trainee.
     *
     * @param gender
     *         Gender of the trainee.
     *
     * @param emailId 
     *         Email-id of the trainee.
     *
     * @param mobileNo
     *         Mobile number of the trainee.
     *
     * @param doorNo 
     *         Door number of the trainee.
     *
     * @param city 
     *         City of the trainee.
     *
     * @param taluk 
     *         Taluk of the trainee.
     *
     * @param state 
     *         State of the trainee.
     *
     * @param district 
     *         District of the trainee.
     *
     * @param pinCode 
     *         Pin Code of the trainee. 
     *
     * @since 1.0
     */  
   public boolean addTrainee(String firstName, String lastName, int id, int batch, String date, String fatherName, String motherName,
            String gender, String emailId, long mobileNo, String doorNo, String city, String taluk, String state,
            String district, int pincode, int age) {
        Trainee trainee = new Trainee(firstName, lastName,id, batch, date, fatherName, motherName, gender, emailId, mobileNo,
            doorNo, city, taluk, state, district, pincode, age);
        return traineeDao.insertTrainee(trainee);
    }

    /**
     * {@code getTrainees} to get all the trainees from {@code TrianeeDao}.
     *
     * @return the all trainees. 
     *
     * @since 1.0
     */ 
    public List<Trainee> getTrainees() {
        return traineeDao.retriveTrainees();
    }

    /**
     * {@code getTraineeByNameAndEmail} get trainee by validating name and
     * email-id.
     *
     * @param name
     *         Name of the trainee to be validate.
     *
     * @param email
     *         Email-id of the trainee to be validate.
     *
     * @return trainee. 
     *
     * @since 1.0
     */ 
    public Trainee getTraineeByNameAndEmail(String name, String email) {
        for (Trainee trainee : traineeDao.retriveTrainees()) {
            if (name.equals(trainee.getFirstName()) && email.equals(trainee.getEmailId())) {
                return trainee;
            }
        }
        return null;
    }

    /**
     * {@code getTraineeIndexByNameAndEmail} to get trainee index by
     * validating trainee name and trainee email.
     *
     * @param name
     *         Name of the trainee to be validate.
     *
     * @param email
     *         Email-id of the trainee to be validate.
     *
     * @return index of the trainee. 
     *
     * @since 1.0
     */
    public int getTraineeIndexByNameAndEmail(String name, String email) {
        int traineeIndex = 0;
        for (Trainee trainee : traineeDao.retriveTrainees()) {
            if (name.equals(trainee.getFirstName()) && email.equals(trainee.getEmailId())) {
                return traineeIndex;
            }
	    traineeIndex++;
        }
        return -1;
    }
   
   /**
     * {@code updateTraineeBy idnex} to pass new trainee to {@code TraineeDao}.
     *
     * @param firstName
     *           First name of trainee.
     *
     * @param lastName 
     *          Last name of trainee.
     *
     * @param id 
     *          Id of trainee.
     * 
     * @param batch
     *          Batch of the trainee.
     *
     * @param date 
     *          Date of birth of the trainee.
     *
     * @param fatherName 
     *          Father name of the trainee.
     *
     * @param motherName 
     *         Mother name of the trainee.
     *
     * @param gender
     *         Gender of the trainee.
     *
     * @param emailId 
     *         Email-id of the trainee.
     *
     * @param mobileNo
     *         Mobile number of the trainee.
     *
     * @param doorNo 
     *         Door number of the trainee.
     *
     * @param city 
     *         City of the trainee.
     *
     * @param taluk 
     *         Taluk of the trainee.
     *
     * @param state 
     *         State of the trainee.
     *
     * @param district 
     *         District of the trainee.
     *
     * @param pinCode 
     *         Pin Code of the trainee.
     * 
     * @param index
     *         Index of the trainee to be update.
     *
     * @since 1.0
     */  
    public boolean updateTraineeByIndex(String firstName, String lastName, int id, int batch, String date,
            String fatherName, String motherName, String gender, String emailId, long  mobileNo, String doorNo, String city,
            String taluk, String state, String district, int pinCode, int age, int index) {
        System.out.println(firstName);
        Trainee trainee = new Trainee(firstName, lastName, id, batch, date, fatherName, motherName, gender, emailId, mobileNo,
            doorNo, city, taluk, state, district, pinCode, age);
        return traineeDao.updateByIndex(index, trainee);
    }

    /**
     * {@code deleteTraineeByNameAndEmail} to delete trinee by validating.
     *
     * @param name
     *         Name of the trainee to be delete.
     *
     * @param email
     *         Email-id of the trainee to be delete.
     *
     * @return true if it is deleted otherwise false. 
     *
     * @since 1.0
     */
    public boolean deleteTraineeByNameAndEmail(String name, String email) {
        for (Trainee trainee : traineeDao.retriveTrainees()) {
            if (name.equals(trainee.getFirstName()) && email.equals(trainee.getEmailId())) {
                return traineeDao.deleteTrainee(trainee);            
            }
        }
        return false;
    }

    /**
     * {@code getTraineeByEmployeeId} to get trainee by validating employee-id.
     *
     * @param employeeId
     *         Employee-Id to be validate.
     *
     * @return trainee. 
     *
     * @since 1.0
     */
    public Trainee getTraineeByEmployeeId(String employeeId) {
        Short traineeEmployeeId = Short.valueOf(employeeId);
        for (Trainee trainee : traineeDao.retriveTrainees()) {
            if (traineeEmployeeId == trainee.getEmployeeId()) {
                return trainee;
            }
        }
        return null;
    }
 
    /**
     * {@code checkIsTraineeById} to check the trainee is available in the 
     * trainer list by validating trainee employee-id.
     *
     * @param employeeId
     *         Employee-Id to be validate.
     *
     * @return returns true if the trainee is present otherwise false. 
     *
     * @since 1.0
     */
    public boolean checkIsTraineeById(String employeeId) {
        Short traineeId = Short.valueOf(employeeId);
        for (Trainee trainee : traineeDao.retriveTrainees()) {
            if (traineeId == trainee.getEmployeeId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@code defaultTrainees} to add default trainees to the list.
     *
     * @since 1.1
     */
    public boolean defaultTrainees() {
        Trainee firstTrainee = new Trainee("Vell", "M", 120, 01, "28-02-2000", "Marimuthu", "Sumathi", "Male", "vell@gmail.com", 
            9857462968L, "1/124", "abc", "abc", "abc", "abc", 624005, 22);
        traineeDao.insertTrainee(firstTrainee);

        Trainee secondTrainee = new Trainee("Guna", "K", 121, 02, "11-08-1996", "kumastha", "Santra", "Male", "guna@gmail.com", 
           9586267419L, "2/124", "abc", "abc", "abc", "abc", 624007, 25);
        traineeDao.insertTrainee(secondTrainee);

        Trainee thirdTrainee = new Trainee("Rama", "A", 122, 03, "31-12-1999", "Murugan", "Parvathi", "Male", "rama@gmail.com", 
            6849683746L, "3/124", "abc", "abc", "abc", "abc", 624009, 23);
        traineeDao.insertTrainee(thirdTrainee);

        Trainee fourthTrainee = new Trainee("Siva", "G", 123, 04, "10-11-2001", "Raja", "Rani", "Male", "siva@gmail.com", 
            8275649285L, "4/124", "abc", "abc", "abc", "abc", 624004, 21);
        traineeDao.insertTrainee(fourthTrainee);
        
        return true;
    }
}