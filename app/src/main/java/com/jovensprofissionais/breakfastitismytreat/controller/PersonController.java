package com.jovensprofissionais.breakfastitismytreat.controller;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 06/01/16
 */


public class PersonController {

    public int changePersonEveryWeek() {
        Calendar week = Calendar.getInstance();
        int weekOfTheYearIntNumber = week.get (Calendar.WEEK_OF_YEAR);
        return weekOfTheYearIntNumber;
    }

//    public Boolean changePersonEverySunday() {
//        Calendar today = Calendar.getInstance();
//        int dayOfTheWeekInNumber = today.get (Calendar.DAY_OF_WEEK);
//        Boolean isSunday = ((dayOfTheWeekInNumber == Calendar.SUNDAY));
//
//        return isSunday;
//    }
}
