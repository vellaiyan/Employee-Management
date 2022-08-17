 /*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 */

package com.ideas2it.utilitis;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.Month;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.text.ParseException;

/**
 * The {@code DateUtil} class represents Date of birth validation. This
 * class is implemented to avoid child labours and future date of birth
 * and invalid dates as well months. 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 * @jls    1.1 Leap year handling
 */
public class DateUtil {
    public static int age;

    /**
     * {@code dateOfBirthValidation} to validate the user date of birth 
     * in different conditions.
     *
     * @param dob
     *          The date of birth need to be validate.
     *
     * @since 1.0
     *
     */
    public static String dateOfBirthValidation(String dob) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        String dateOfBirth = "";
        try {
            //Month month = FEBRUARY;
            Date dateOfBirthParse = simpleDateFormat.parse(dob);            
            Instant instant = dateOfBirthParse.toInstant();
            ZonedDateTime timeZone = instant.atZone(ZoneId.systemDefault());
            LocalDate givenDate = timeZone.toLocalDate();
            int birthYear = givenDate.getYear();
            int givenDay = givenDate.getDayOfMonth();
            boolean leapYear = (((birthYear % 4 == 0) && (birthYear % 100!= 0)) || (birthYear%400 == 0));
            int currentYear = LocalDate.now().getYear();    

            //find given date of birth is valid or not.   
            if (birthYear > currentYear) {
                dateOfBirth = "max";
            } else if (birthYear <= 1950) {
                dateOfBirth = "low";
            } else if ((currentYear - birthYear) <= 18 ) {
                dateOfBirth = "min";
            } else if(((leapYear == true) &&((givenDate.getMonth().toString().equals("FEBRUARY")) == true)) && (givenDay>29)) {
                dateOfBirth = "low";
            }
            else {
                dateOfBirth = dob;
            }
            Period agePeriod = Period.between(givenDate, LocalDate.now());
            int userAge = agePeriod.getYears();
            age = userAge;
        } catch (ParseException e) {
            dateOfBirth = "invalid";            
        }
        return dateOfBirth;
    }

    /**
     * {@code timeDelay} to create a time delay.
     *
     * @param delay
     *          Time delay in seconds.
     *
     * @since 1.0
     *
     */   
    public static void timeDelay(int delay) {
        try {
            System.out.print("Loading.");
            Thread.sleep(delay);
            System.out.print(".");
            Thread.sleep(delay);
            System.out.print(".");
            Thread.sleep(delay);
            System.out.print(".");
            Thread.sleep(delay);
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b");
            Thread.sleep(delay);
            System.out.println("                       ");
        } catch (Exception e) {

        }
    }
}