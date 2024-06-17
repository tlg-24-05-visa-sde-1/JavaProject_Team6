package com.austinactivities;

import java.util.List;

public interface Activity {
    String getName();
    String getDescription();
    String getLocation();
    List<Interests> getType();
}
