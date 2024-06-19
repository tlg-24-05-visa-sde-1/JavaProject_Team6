package com.austinactivities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class MusicActivity implements Activity, Serializable {
    private final String musicName;
    private final String musicDescription;
    private final String musicLocation;
    private final List<Interests> interestsList;

    public MusicActivity(String musicName, String musicDescription, String musicLocation, List<Interests> interestsList) {
        this.musicName = musicName;
        this.musicDescription = musicDescription;
        this.musicLocation = musicLocation;
        // validate that only allowed Interests are here
        for (Interests interest: interestsList) {
            if (!interest.equals(Interests.Music_Concert) && !interest.equals(Interests.Music_Festival) && !interest.equals(Interests.Live_Music)) {
                throw new RuntimeException("Invalid Interests for MusicActivity");
            }
        }
        this.interestsList = interestsList;
    }

    @Override
    public String getName() {
        return musicName;
    }

    @Override
    public String getDescription() {
        return musicDescription;
    }

    @Override
    public String getLocation() {
        return musicLocation;
    }

    @Override
    public List<Interests> getType() {
        return interestsList;
    }

    @Override
    public String toString() {
        return getName() + " is here! Enjoy music at " + getLocation() + " more details to get you going: " + getDescription();
    }
}
