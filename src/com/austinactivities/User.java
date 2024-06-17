package com.austinactivities;
import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<Interests> interestList=new ArrayList<>();

    public User() {
    }
    public User(String name) {
        setUser(userName);
    }

    public void setUser(String userName) {
        //TODO verify if username in database, return exception if found
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void addInterest(Interests i) {
        addInterest(i);
    }
}