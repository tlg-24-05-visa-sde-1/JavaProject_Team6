package com.austinactivities;


import java.util.*;

public class RecommendedActivities{

   public  List<Activity> generateActivityList(List<Interests> listOfInterests){
        List<Activity> generatedActivities = new ArrayList<>();
        //int wantedActRecs = 1;

        for (Interests interests : listOfInterests){
            generatedActivities.addAll(loadActivities(interests));
        }
        //Randomizes the list
        Collections.shuffle(generatedActivities, new Random());

        //Cuts down the list to yield number of recommended activities wanted
        //List<Activity>  cutDownListGeneratedActivities= generatedActivities.subList(0,1);
        //return cutDownListGeneratedActivities;
       return generatedActivities;
    }

    private  List<Activity> loadActivities(Interests interests){
        List<Activity> activitiesOfInterest = new ArrayList<>();

        LoadActivitiesAndCreate start = new LoadActivitiesAndCreate();
        start.loadActivitiesFromTxt("./resources/activities-examples.txt");
        activitiesOfInterest.addAll(start.getActivitiesOnInterests(interests));

        return activitiesOfInterest;
    }

}