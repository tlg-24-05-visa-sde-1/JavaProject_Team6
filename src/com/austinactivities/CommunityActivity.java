package com.austinactivities;

import java.util.List;

public class CommunityActivity implements Activity {

    private final String communityName;
    private final String communityDescription;
    private final String communityLocation;
    private final List<Interests> interestsList;

    public CommunityActivity(String communityName, String communityDescription, String communityLocation, List<Interests> interestsList) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.communityLocation = communityLocation;
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
        return "CommunityActivity{" +
                "communityName='" + getName() + '\'' +
                ", communityDescription='" + getDescription() + '\'' +
                ", communityLocation='" + getLocation() + '\'' +
                ", interestsList=" + getType() +
                '}';
    }
}
