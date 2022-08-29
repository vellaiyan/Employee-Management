/*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.utilitis; 

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
    public static String NAME_PATTERN = "^[a-zA-Z]{1,9}$";                                                     /* name valemployeeIdation     */
    public static String GENDER_PATTERN = "^male|female$";                                                    /* gender valemployeeIdation   */
    public static String PHONE_PATTERN = "^[6-9][0-9]{9}$";                                                  /* Phone no valemployeeIdation */
    public static String DOOR_PATTERN = "^([0-9]+)/([0-9][0-9][0-9])$";                                     /* door no valemployeeIdation  */
    public static String PIN_PATTERN = "^([6]+)([0-9][0-9][0-9][0-9][0-9])$";                              /* pin code valemployeeIdation */ 
    public static String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";          /* email valemployeeIdation    */

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