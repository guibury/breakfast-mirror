package com.jovensprofissionais.breakfastitismytreat.entity;


public class UserRating {

    String id;
    String name;
    int rate;
    int totalTimesVoted;

    public UserRating(String id, String name, int rate){
        this.id=id;
        this.name = name;
        this.rate = rate;
        this.totalTimesVoted = 0;
    }

    public UserRating(String name, int rate){
        this.id=id;
        this.name = name;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
