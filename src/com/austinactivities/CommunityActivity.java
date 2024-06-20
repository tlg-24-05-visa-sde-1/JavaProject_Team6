package com.austinactivities;

import java.io.Serializable;
import java.util.List;

public class CommunityActivity implements Activity, Serializable {

    private final String communityName;
    private final String communityDescription;
    private final String communityLocation;
    private final List<Interests> interestsList;

    public CommunityActivity(String communityName, String communityDescription, String communityLocation, List<Interests> interestsList) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.communityLocation = communityLocation;
        // validate that only allowed Interests are here
        for (Interests interest: interestsList) {
            if (!interest.equals(Interests.Museums) && !interest.equals(Interests.Group_Fitness) && !interest.equals(Interests.Community_Gathering) && !interest.equals(Interests.Celebrations)) {
                throw new RuntimeException("Invalid Interests for MusicActivity");
            }
        }
        this.interestsList = interestsList;
    }

    @Override
    public String getName() {
        return communityName;
    }

    @Override
    public String getDescription() {
        return communityDescription;
    }

    @Override
    public String getLocation() {
        return communityLocation;
    }

    @Override
    public List<Interests> getType() {
        return interestsList;
    }

    @Override
    public String toString() {
        return "Come join the Austin community at " + getLocation() + " for the " + getName() + ", details: " + getDescription();
    }
}
