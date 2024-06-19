package com.austinactivities;

import java.io.Serializable;
import java.util.List;

public class OutdoorActivity implements Activity, Serializable {
    private final String outdoorName;
    private final String outdoorDescription;
    private final String outdoorLocation;
    private final List<Interests> interestsList;

    public OutdoorActivity(String outdoorName, String outdoorDescription, String outdoorLocation, List<Interests> interestsList) {
        this.outdoorName = outdoorName;
        this.outdoorDescription = outdoorDescription;
        this.outdoorLocation = outdoorLocation;
        // validate that only allowed Interests are here
        for (Interests interest: interestsList) {
            if (!interest.equals(Interests.Water_Activity) && !interest.equals(Interests.Hiking_Or_Parks) && !interest.equals(Interests.Sightseeing)) {
                throw new RuntimeException("Invalid Interests for MusicActivity");
            }
        }
        this.interestsList = interestsList;
    }

    @Override
    public String getName() {
        return outdoorName;
    }

    @Override
    public String getDescription() {
        return outdoorDescription;
    }

    @Override
    public String getLocation() {
        return outdoorLocation;
    }

    @Override
    public List<Interests> getType() {
        return interestsList;
    }

    @Override
    public String toString() {
        return "The sun calls for you to head to " + getName() + " and enjoy " + getDescription() + " at " + getLocation();
    }
}
