package com.austinactivities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // regular expression pattern for entry
        Pattern pattern = Pattern.compile("\"name\": \"(.*?)\", \"description\": \"(.*?)\", \"location\": \"(.*?)\", \"type\": \\[(.*?)\\]");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {

                    // Matcher obj to match line w pattern
                    Matcher matcher = pattern.matcher(line);

                    // if pattern matches line, get data
                    if (matcher.find()) {
                        String name = matcher.group(1);
                        String description = matcher.group(2);
                        String location = matcher.group(3);
                        // for type
                        String[] interestsArray = matcher.group(4).split(", ");
                        List<Interests> interestsList = new ArrayList<>();

                        // convert string to enum and add
                        for (String interest: interestsArray) {
                            interestsList.add(Interests.valueOf(interest.replace("\"", "").trim()));
                        }

                        Activity activity = createActivity(name, description, location, interestsList);
                        if (activity != null) {
                            activities.add(activity);
                        }
                    } else {
                        System.err.println("Invalid line format: " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // error if opening/read file
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

    public void dump(List<Activity> activities) {
        for (Activity activity: activities) {
            System.out.println(activity);
        }
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
