/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.service;

import com.ideas2it.dao.HumanResourceDao;
import com.ideas2it.model.HumanResource;

import java.util.LinkedList;

/**
 * The {@code HumanResourceService} class implemented to support add, get, 
 * Assigned trainees.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Get assignd trainees.
 *
 */

public class HumanResourceService {
    HumanResourceDao humanResourceDao = new HumanResourceDao();

    /**
     * {@code addTraines} pass all the a trainees to 
     * HumanResourceDao.
     *
     * @param humanResource
     *          object of HumanResource model
     * @param isNeed
     *          used to creat new object for trainees.
     * 
     * @since 1.0
     *
     */
    public void addTrainees (HumanResource humanResource, boolean isNeed) {
        humanResourceDao.insertAssignedTrainees(humanResource, isNeed);
    }

    /**
     * {@code getAssignedTrainees} retrive all the assigned trainees 
     * from HumanResourceDao.
     * 
     * @since 1.0
     *
     */
    public LinkedList<HumanResource> getAssignedTrainees() {
        return humanResourceDao.retriveAssignedTrainees();
     }
}