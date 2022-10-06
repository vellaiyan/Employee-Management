 /*
 * Copyright (c) 2021, 2022, Ideas2it and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ideas2it.utilitis;

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
    public static String validateDateOfBirth(String dob, String choosenDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        String dateOfBirth = "";
        try {
            if(choosenDate.equals("dob")) {
                Date dateOfBirthParse = simpleDateFormat.parse(dob);            
                Instant instant = dateOfBirthParse.toInstant();
                ZonedDateTime timeZone = instant.atZone(ZoneId.systemDefault());
                LocalDate givenDate = timeZone.toLocalDate();
                int birthYear = givenDate.getYear();
                int givenDay = givenDate.getDayOfMonth();
                boolean leapYear = (((birthYear % 4 == 0) && (birthYear % 100!= 0)) || (birthYear%400 == 0));
                int currentYear = LocalDate.now().getYear();    

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
            } else {
                return dateOfBirth = dob;
            }
        } catch (ParseException e) {
            dateOfBirth = "invalid";            
        }
        return dateOfBirth;
    }

    public static String validateAssignAndCompletionDate(String date, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        String assignDate = "";
        try {
            Date AssignDateParse = simpleDateFormat.parse(date);
            return date;
        } catch(ParseException e) {
            return "Not valid";
        }
    }

    public static String validateCompleteDate(String date, String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        String assignDate = "";
        try {
            Date AssignDateParse = simpleDateFormat.parse(date);
            return date;
        } catch(ParseException e) {
            return "Not valid";
        }
    }
  
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