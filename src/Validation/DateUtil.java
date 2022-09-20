 /*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * The {@code DateUtil} class represents Date of birth validation. This class is implemented to avoid child labours and future date of birth
 * and invalid dates as well months. 
 *
 * @author Vellaiyan
 *
 * @since  1.0
 *
 * @jls    1.1 Leap year handling
 */
public class DateUtil {

    /**
     * {@code validateDateOfBirth} to validate date of birth.
     *
     * @param dateOfBirth
     *       Date of birth need to be validate.
     *
     * @return dateOfBirth.
     *
     * @since 1.0
     * 
     */ 
    public static String validateDateOfBirth(String dateOfBirth, String choosenDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        try {
            if (choosenDate.equals("dob")) {
                Date dateOfBirthParse = simpleDateFormat.parse(dateOfBirth);            
                Instant instant = dateOfBirthParse.toInstant();
                ZonedDateTime timeZone = instant.atZone(ZoneId.systemDefault());
                LocalDate givenDate = timeZone.toLocalDate();
                int birthYear = givenDate.getYear();
                int givenDay = givenDate.getDayOfMonth();
                boolean leapYear = (((birthYear % 4 == 0) && (birthYear % 100!= 0)) || (birthYear % 400 == 0));
                int currentYear = LocalDate.now().getYear();    

                if (birthYear > currentYear) {
                    dateOfBirth = "max";
                } else if (birthYear <= 1950) {
                    dateOfBirth = "low";
                } else if ((currentYear - birthYear) <= 18 ) {
                    dateOfBirth = "min";
                } else if (((leapYear == true) &&((givenDate.getMonth().toString().equals("FEBRUARY")) == true)) && (givenDay>29)) {
                    dateOfBirth = "low";
                }
                else {
                    dateOfBirth = dateOfBirth;
                }
            } else {
                return dateOfBirth = dateOfBirth;
            }
        } catch (ParseException parseException) {
            dateOfBirth = "invalid";            
        }
        return dateOfBirth;
    }

    /**
     * {@code validateAssignDateAndCompletionDate} to validate assigned and completion date.
     *
     * @param date
     *       Date need to be validate.
     * 
     * @param type
     *       Date type (assign date / completion date).
     *
     * @throws CustomException.
     *
     * @return date.
     *
     * @since 1.0
     * 
     */ 
    public static String validateAssignDateAndCompletionDate(String date, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        String assignDate = "";
        try {
            Date AssignDateParse = simpleDateFormat.parse(date);
            return date;
        } catch (ParseException parseException) {
            return "Not valid";
        }
    }

    /**
     * {@code timeDelay} to create a timedelay
     *
     * @param delay
     *       delay in seconds.
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
        } catch (Exception exception) {

        }
    }
}