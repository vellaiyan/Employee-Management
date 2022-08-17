/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import com.ideas2it.model.Trainee;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The {@code TraineeDao} class implemented to insert, retrive, update, delete
 * trainees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Delete functionality using index.
 */

public class TraineeDao {
    private static List<Trainee> trainees = new LinkedList<Trainee>();

    /**
     * {@code insertTrainee} to insert the new trainee in trainees list.
     *
     * @param trainee
     *          Trainee object to be insert.
     * 
     * @since 1.0
     *
     */ 
    public boolean insertTrainee(Trainee trainee) {
        trainees.add(trainee);
        return true;
    }

    /**
     * {@code retriveTrainees} to retrive all the trainees.
     *
     * @return list of trainees.
     * 
     * @since 1.0
     *
     */ 
    public List<Trainee> retriveTrainees() {
        return trainees;
    } 

    /**
     * {@code updateByIndex} to update trainee by index.
     *
     * @param index
     *          Index of the trainee to be update.
     * 
     * @param trainee
     *          Trainee object to be update.
     *
     * @since 1.0
     *
     */ 
    public boolean updateByIndex(int index, Trainee trainee) {
        trainees.set(index, trainee);
        return true;
    }

    /**
     * {@code deleteByIndex} to delete trainee by index
     *
     * @param index
     *          Index of the trainee to be delete.
     * 
     * @since 1.0
     *
     */ 
    public boolean deleteTrainee(Trainee trainee) {
        return trainees.remove(trainee);
    }
}