package com.jovensprofissionais.breakfastitismytreat.entity;

/**
 * Created with Eclipse IDE.
 * User: Thiago Osiro
 * Date: 06/01/16
 */

public class UserRating {

    String name;
    int rate;
    int totalTimesVoted;

    public UserRating(String name, int rate) {
        this.name = name;
        this.rate = rate;
        this.totalTimesVoted = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTimesVoted() {
        return totalTimesVoted;
    }

    public void incTotalTimesVoted() {
        this.totalTimesVoted ++;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
