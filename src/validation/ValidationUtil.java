/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.utils; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code ValidationUtil} class represents validation. This class is implemented to avoid wrong name, phone number, emails.
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 PinCode validation.
 */

public class ValidationUtil {
    public static String EMPLOYEE_ID_PATTERN = "^[0-9][0-9]$";
    public static String NAME_PATTERN = "^[a-zA-Z]{1,9}$";               
    public static String GENDER_PATTERN = "^male|female |others$";           
    public static String PHONE_PATTERN = "^[6-9][0-9]{9}$";                            
    public static String DOOR_PATTERN = "^([0-9]+)/([0-9][0-9][0-9])$";                                   
    public static String PIN_PATTERN = "^([6]+)([0-9][0-9][0-9][0-9][0-9])$";                            
    public static String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";         

    /**
     * {@code inputCheckingByRegex} to check the given user input is valid or not.
     *
     * @param regex
     *       regex for validation.
     *
     * @param value
     *       User input need to be check.
     *
     *
     * @return boolean.
     *
     * @since 1.0
     * 
     */ 
    public static boolean inputCheckingByRegex (String regex, String value) {
        boolean isvalid = false;
        Pattern inputPattern = Pattern.compile(regex);
        Matcher inputMatcher = inputPattern.matcher(value);
        if (inputMatcher.find()) {
            isvalid = true;
        } 

        return isvalid;
    }
}