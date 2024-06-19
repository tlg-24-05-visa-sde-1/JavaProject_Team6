package com.austinactivities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class User implements Serializable {
    private String userName;
    private List<Interests> interestList;
    private List<Activity> activityList;

    public User() {
    }
    public User(String name) {
        setUser(name);
        this.interestList = new ArrayList<>();
        this.activityList = new ArrayList<>();
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public List<Interests> getInterestList() {
        return Collections.unmodifiableList(interestList);
    }

    public void addInterest(Interests i) {
        interestList.add(i);
        System.out.println(i + " has been added to your interests.");
    }

    public void saveActivityList(ArrayList<Activity> activityList) {
        this.activityList.addAll(activityList);
    }

    public List<Activity> getActivityList() {
        return Collections.unmodifiableList(activityList);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", interestList=" + interestList +
                ", activityList=" + activityList +
                '}';
    }
}