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

public class DateUtil {
    public static String dateOfBirthValidation(String dob, String choosenDate) {
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
            } else {
                return dateOfBirth = dob;
            }
        } catch (ParseException e) {
            dateOfBirth = "invalid";            
        }
        return dateOfBirth;
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