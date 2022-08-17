/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.dao;

import java.util.LinkedList;
import java.util.List;

import com.ideas2it.model.HumanResource;

/**
 * The {@code HumanResourceDao} class represents storing and retriving
 * temporaty trainees list.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Adding temporary trainees to list.
 */

public class HumanResourceDao {
    private static LinkedList<HumanResource> trainees = new LinkedList<HumanResource>();

    /**
     * {@code insertAssignedTrainees} insert all the assigned trainees to 
     * trainees list.
     *
     * @param humanResource
     *          object of HumanResource model
     * @param isNeed
     *          used to creat new object for trainees.
     *
     * @return list of trainees
     * 
     * @since 1.0
     *
     */
    public void insertAssignedTrainees(HumanResource humanResource, boolean isNeed) {
        if (isNeed) {
            trainees = new LinkedList<HumanResource>();
            trainees.add(humanResource);
        } else {
            trainees.add(humanResource);
        }
    }


    /**
     * {@code retriveAssignedTrainees} retrive all the assigned trainees
     * from the trainees list.
     *
     * @return list of trainees
     * 
     * @since 1.0
     *
     */
    public LinkedList<HumanResource> retriveAssignedTrainees() {
        return trainees;
    } 
}