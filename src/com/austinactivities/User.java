package com.austinactivities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {
    private String userName;
    private List<Interests> interestList;

    public User() {
    }
    public User(String name) {
        setUser(userName);
        this.interestList = new ArrayList<>();
    }

    public void setUser(String userName) {
        //TODO verify if username in database, return exception if found
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
    }
}