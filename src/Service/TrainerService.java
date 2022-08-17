/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.service;

import com.ideas2it.dao.TrainerDao;
import com.ideas2it.model.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code TrainerService} class implemented to support add, get, 
 * delete, functionalities to trainers.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding default trainers.
 */

public class TrainerService {
    private TrainerDao trainerDao = new TrainerDao();

   /**
     * {@code addTrainer} to pass new trainer to {@code TrainerDao}.
     *
     * @param firstName
     *           First name of trainer.
     *
     * @param lastName 
     *          Last name of trainer.
     *
     * @param id 
     *          Id of trainer.
     * 
     * @param batch
     *          Batch of the trainer.
     *
     * @param date 
     *          Date of birth of the trainer.
     *
     * @param fatherName 
     *          Father name of the trainer.
     *
     * @param motherName 
     *         Mother name of the trainer.
     *
     * @param gender
     *         Gender of the trainer.
     *
     * @param emailId 
     *         Email-id of the trainer.
     *
     * @param mobileNo
     *         Mobile number of the trainer.
     *
     * @param doorNo 
     *         Door number of the trainer.
     *
     * @param city 
     *         City of the trainer.
     *
     * @param taluk 
     *         Taluk of the trainer.
     *
     * @param state 
     *         State of the trainer.
     *
     * @param district 
     *         District of the trainer.
     *
     * @param pinCode 
     *         Pin Code of the trainer. 
     *
     * @since 1.0
     */  
    public boolean addTrainer(String firstName, String lastName, int employeeId, String subject, String experience, String date,
            String fatherName, String motherName, String gender, String emailId, long mobileNo, String doorNo,
            String city, String taluk, String state, String district, int pinCode, int age) {
        Trainer trainer = new Trainer(firstName, lastName, employeeId, subject, experience, date, fatherName, motherName, gender, 
            emailId, mobileNo, doorNo, city, taluk, state, district, pinCode, age);
        trainerDao.insertTrainer(trainer);
        return true;
    }

    /**
     * {@code getTrainers} to get all the trainers from {@code TrianerDao}.
     *
     * @return the all trainers. 
     *
     * @since 1.0
     */ 
    public List<Trainer> getTrainers() {
        return trainerDao.retriveTrainers();
    }

    /**
     * {@code getTrainerByNameAndEmail} get trainer by validating name and
     * email-id.
     *
     * @param name
     *         Name of the trainer to be validate.
     *
     * @param email
     *         Email-id of the trainer to be validate.
     *
     * @return trainer. 
     *
     * @since 1.0
     */
    public Trainer getTrainerByNameAndEmail(String name, String email) {
        for (Trainer trainer : trainerDao.retriveTrainers()) {
            if (name.equals(trainer.getFirstName()) && email.equals(trainer.getEmailId())) {
                return trainer;
            }
        }
        return null;
    }

    /**
     * {@code getTrainerByNameAndEmail} get trainer by validating name and
     * email-id.
     *
     * @param name
     *         Name of the trainer to be validate.
     *
     * @param email
     *         Email-id of the trainer to be validate.
     *
     * @return trainer. 
     *
     * @since 1.0
     */
    public int getTrainerIndexByNameAndEmail(String name, String email) {
        int trainerIndex = 0;;
        for (Trainer trainer : trainerDao.retriveTrainers()) {
            if (name.equals(trainer.getFirstName()) && email.equals(trainer.getEmailId())) {
                return trainerIndex;
            }
	    trainerIndex++;
        }
        return -1;
    }


  /**
     * {@code updateTrainerByIndex} to pass new trainer to {@code TrainerDao}.
     *
     * @param firstName
     *           First name of trainer.
     *
     * @param lastName 
     *          Last name of trainer.
     *
     * @param id 
     *          Id of trainer.
     * 
     * @param batch
     *          Batch of the trainer.
     *
     * @param date 
     *          Date of birth of the trainer.
     *
     * @param fatherName 
     *          Father name of the trainer.
     *
     * @param motherName 
     *         Mother name of the trainer.
     *
     * @param gender
     *         Gender of the trainer.
     *
     * @param emailId 
     *         Email-id of the trainer.
     *
     * @param mobileNo
     *         Mobile number of the trainer.
     *
     * @param doorNo 
     *         Door number of the trainer.
     *
     * @param city 
     *         City of the trainer.
     *
     * @param taluk 
     *         Taluk of the trainer.
     *
     * @param state 
     *         State of the trainer.
     *
     * @param district 
     *         District of the trainer.
     *
     * @param pinCode 
     *         Pin Code of the trainer.
     * 
     * @param index
     *         Index of the trainer to be update.
     *
     * @since 1.0
     */ 
    public boolean upateTrainerByIndex(String firstName, String lastName, int id, String major, String experience, String date,String fatherName,
            String motherName,String gender, String emailId, long mobileNo,String doorNo,String city,String taluk,String state,  
            String district, int pinCode, int age, int index) {
        System.out.println(firstName);
        Trainer trainer = new Trainer(firstName, lastName, id, major, experience, date, fatherName, motherName, gender, emailId, mobileNo,
            doorNo, city, taluk, state, district, pinCode, age);
        return trainerDao.updateByIndex(index,trainer);
    }    

    /**
     * {@code deleteTrainerByNameAndEmail} to delete triner by validating.
     *
     * @param name
     *         Name of the trainer to be delete.
     *
     * @param email
     *         Email-id of the trainer to be delete.
     *
     * @return true if it is deleted otherwise false. 
     *
     * @since 1.0
     */
    public boolean deleteTrainerByNameAndEmail(String name, String email) {
        for (Trainer trainer : trainerDao.retriveTrainers()) {
            if (name.equals(trainer.getFirstName()) && email.equals(trainer.getEmailId())) {  
                return trainerDao.deleteTrainer(trainer);             
            }
        }
        return false;
    }

    /**
     * {@code getTrainerByEmployeeId} to get trainer by validating employee-id.
     *
     * @param employeeId
     *         Employee-Id to be validate.
     *
     * @return trainer. 
     *
     * @since 1.0
     */
    public Trainer getTrainerByEmployeeId(String employeeId) {
        Short trainerId = Short.valueOf(employeeId);
        for (Trainer trainer : trainerDao.retriveTrainers()) {
            if (trainerId == trainer.getEmployeeId()) {
                return trainer;
            }
        }
        return null;
    }

    /**
     * {@code checkIsTrainerById} to check the trainer is available in the 
     * trainer list by validating trainer employee-id.
     *
     * @param employeeId
     *         Employee-Id to be validate.
     *
     * @return returns true if the trainer is present otherwise false. 
     *
     * @since 1.0
     */
    public boolean checkIstrainerById(String employeeId) {
        boolean isTrainer = false;
        Short trainerId = Short.valueOf(employeeId);
        for (Trainer trainer : trainerDao.retriveTrainers()) {
            if (trainerId == trainer.getEmployeeId()) {
                return true;
            }
        }
        return false;
    }


    /**
     * {@code defaultTrainees} to add default trainers to the list.
     *
     * @since 1.1
     */
    public boolean defaultTrainers() {
        Trainer firstTrainer = new Trainer("Raj", "M", 124, "java", "07", "27-06-1992", "Kumar", "Uma", "male", "raj@gmail.com", 9792643756L,"3/175",                    "cde","abc", "def", "abc", 624008, 30);
        trainerDao.insertTrainer(firstTrainer);

        Trainer secondTrainer = new Trainer("Joy", "V", 125, "HTML", "08", "27-06-1993", "Akilan", "Maha", "male", "joy@gmail.com", 8427647375L, "3/175",
           "cde", "abc", "def", "abc", 624008, 29);
        trainerDao.insertTrainer(secondTrainer);
        return true;
    }
}