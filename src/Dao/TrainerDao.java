/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import com.ideas2it.model.Trainer;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TrainerDao} class implemented to insert, retrive, update, delete
 * trainers.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Delete functionality using index.
 */

public class TrainerDao {
    private static List<Trainer> trainers = new ArrayList<Trainer>();

    /**
     * {@code insertTrainer} to insert the new trainer in trainers list.
     *
     * @param trainer
     *          Trainer object to be insert.
     * 
     * @since 1.0
     *
     */ 
    public boolean insertTrainer(Trainer trainer) {
        trainers.add(trainer);  
        return true;
    }

    /**
     * {@code retriveTrainers} to retrive all the trainers.
     *
     * @return list of trainers.
     * 
     * @since 1.0
     *
     */ 
    public List<Trainer> retriveTrainers() {
        return trainers;
    }

    /**
     * {@code updateByIndex} to update trainer by index.
     *
     * @param index
     *          Index of the trainer to be update.
     * 
     * @param trainer
     *          Trainer object to be update.
     *
     * @since 1.0
     *
     */ 
    public boolean updateByIndex(int index, Trainer trainer) {
        trainers.set(index, trainer);
        return true;
    }

   /**
     * {@code deleteByIndex} to delete trainer by index
     *
     * @param index
     *          Index of the trainer to be delete.
     * 
     * @since 1.0
     *
     */   
    public boolean deleteTrainer(Trainer trainer) {
        return trainers.remove(trainer);
    } 
}