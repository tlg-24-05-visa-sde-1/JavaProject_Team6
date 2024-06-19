package com.austinactivities;

import java.io.Serializable;
import java.util.List;

public class FoodActivity implements Activity, Serializable {
    private final String foodName;
    private final String foodDescription;
    private final String foodLocation;
    private final List<Interests> interestsList;

    public FoodActivity(String foodName, String foodDescription, String foodLocation, List<Interests> interestsList) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodLocation = foodLocation;
        // validate that only allowed Interests are here
        for (Interests interest: interestsList) {
            if (!interest.equals(Interests.Restaurants) && !interest.equals(Interests.Pop_Up_Food) && !interest.equals(Interests.Food_Trucks)) {
                throw new RuntimeException("Invalid Interests for MusicActivity");
            }
        }
        this.interestsList = interestsList;
    }

    @Override
    public String getName() {
        return foodName;
    }

    @Override
    public String getDescription() {
        return foodDescription;
    }

    @Override
    public String getLocation() {
        return foodLocation;
    }

    @Override
    public List<Interests> getType() {
        return interestsList;
    }

    @Override
    public String toString() {
        return "Time to get some good eats at " + getName() + " people have said: " + getDescription() + " located at " + getLocation();
    }
}
