package com.austinactivities;


import java.util.*;

public class RecommendedActivities{

    public static List<Activity> generateActivityList(List<Interests> listOfInterests){
        List<Activity> generatedActivities = new ArrayList<>();
        int wantedActRecs = 5;

        for (Interests interests : listOfInterests){
            generatedActivities.addAll(loadActivities(interests));
        }

        //Randomizes the list
        Collections.shuffle(generatedActivities, new Random());

        //Cuts down the list to yield number of recommended activities wanted
        List<Activity>  cutDownListGeneratedActivities= generatedActivities.subList(0,wantedActRecs);
        return cutDownListGeneratedActivities;
    }

    private static List<Activity> loadActivities(Interests interests){
        List<Activity> activitiesOfInterest = new ArrayList<>();
        //TODO
        return activitiesOfInterest;
    }

    //WorkingExampleBelow

    /*public static List<Activity> generateActivityList(List<Interests> listOfInterests){
        List<Activity> generatedActivities = new ArrayList<>();

        List<String> interestActivities = new ArrayList<>();
        for (Interests interests : listOfInterests){
            switch(interests){
                case Water_Activity ->interestActivities.add("Water_Activity");
                case Museums->interestActivities.add("Museums");
                case Live_Music -> interestActivities.add("Live_Music");
                case Food_Trucks -> interestActivities.add("Food_Trucks");
                case Restaurants -> interestActivities.add("Restaurants");
                default -> System.out.println("Unknown Interest: "+ interests);
            }
        }
        System.out.println(interestActivities.toString());
        Collections.shuffle(interestActivities, new Random());
        System.out.println(interestActivities.toString());
        List<String>  substringofList = interestActivities.subList(0, 2);
        System.out.println(substringofList.toString());
        return generatedActivities;
    }*/


}