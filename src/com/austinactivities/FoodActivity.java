package com.austinactivities;

import java.util.List;

public class FoodActivity implements Activity {
    private final String foodName;
    private final String foodDescription;
    private final String foodLocation;
    private final List<Interests> interestsList;

    public FoodActivity(String foodName, String foodDescription, String foodLocation, List<Interests> interestsList) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodLocation = foodLocation;
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
        return "FoodActivity{" +
                "foodName='" + foodName + '\'' +
                ", foodDescription='" + foodDescription + '\'' +
                ", foodLocation='" + foodLocation + '\'' +
                ", interestsList=" + interestsList +
                '}';
    }
}
