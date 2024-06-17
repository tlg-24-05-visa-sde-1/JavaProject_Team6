package com.austinactivities;

import java.util.List;

public class MusicActivity implements Activity {
    private final String musicName;
    private final String musicDescription;
    private final String musicLocation;
    private final List<Interests> interestsList;

    public MusicActivity(String musicName, String musicDescription, String musicLocation, List<Interests> interestsList) {
        this.musicName = musicName;
        this.musicDescription = musicDescription;
        this.musicLocation = musicLocation;
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
        return "MusicActivity{" +
                "musicName='" + musicName + '\'' +
                ", musicDescription='" + musicDescription + '\'' +
                ", musicLocation='" + musicLocation + '\'' +
                ", interestsList=" + interestsList +
                '}';
    }
}
