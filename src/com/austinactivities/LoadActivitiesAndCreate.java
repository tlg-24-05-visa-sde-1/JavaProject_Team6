package com.austinactivities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Load activities from txt file for object creation and to be used in app
 */
public class LoadActivitiesAndCreate {
    private final List<Activity> activities;

    public LoadActivitiesAndCreate() {
        activities = new ArrayList<>();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    // methods
    public void loadActivitiesFromTxt(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(", (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    if (parts.length != 4) {
                        System.err.println("Invalid line format: " + line);
                        continue;
                    }

                    String name = extractValue(parts[0]);
                    String description = extractValue(parts[1]);
                    String location = extractValue(parts[2]);
                    String[] interestsArray = extractValue(parts[3]).replace("[", "").replace("]", "").split(", ");
                    List<Interests> interestsList = new ArrayList<>();
                    for (String interest : interestsArray) {
                        interestsList.add(Interests.valueOf(interest));
                    }

                    // Create a new activity based on interests

                    Activity activity = createActivity(name, description, location, interestsList);



                    // Null check
                    if (activity != null) {
                        activities.add(activity);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Activity createActivity(String name, String description, String location, List<Interests> interestsList) {
        Activity activity = null;
        if (interestsList.contains(Interests.Water_Activity) || interestsList.contains(Interests.Hiking_Or_Parks) || interestsList.contains(Interests.Sightseeing)) {
            activity = new OutdoorActivity(name, description, location, interestsList);
        } else if (interestsList.contains(Interests.Restaurants) || interestsList.contains(Interests.Pop_Up_Food) || interestsList.contains(Interests.Food_Trucks)) {
            activity = new FoodActivity(name, description, location, interestsList);
        } else if (interestsList.contains(Interests.Music_Festival) || interestsList.contains(Interests.Music_Concert) || interestsList.contains(Interests.Live_Music)) {
            activity = new MusicActivity(name, description, location, interestsList);
        } else if (interestsList.contains(Interests.Museums) || interestsList.contains(Interests.Group_Fitness) || interestsList.contains(Interests.Community_Gathering) || interestsList.contains(Interests.Celebrations)) {
            activity = new CommunityActivity(name, description, location, interestsList);
        }
        return activity;
    }

    private String extractValue(String value) {
        return value.split(": ")[1].replace("\"", "").trim();
    }

    // helper method to get activities based on interests
    public void getActivitiesOnInterests(String preference) {
        System.out.println("Retrieving activities from preference: " + preference);
        Interests interest = Interests.valueOf(preference);
        for (Activity activity : activities) {
            if (activity.getType().contains(interest)) {
                System.out.println(activity);
            }
        }
    }
}
