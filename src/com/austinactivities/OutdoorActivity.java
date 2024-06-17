package com.austinactivities;

import java.util.List;

public class OutdoorActivity implements Activity {
    private final String outdoorName;
    private final String outdoorDescription;
    private final String outdoorLocation;
    private final List<Interests> interestsList;

    public OutdoorActivity(String outdoorName, String outdoorDescription, String outdoorLocation, List<Interests> interestsList) {
        this.outdoorName = outdoorName;
        this.outdoorDescription = outdoorDescription;
        this.outdoorLocation = outdoorLocation;
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
        return "OutdoorActivity{" +
                "outdoorName='" + outdoorName + '\'' +
                ", outdoorDescription='" + outdoorDescription + '\'' +
                ", outdoorLocation='" + outdoorLocation + '\'' +
                ", interestsList=" + interestsList +
                '}';
    }
}
