package com.austinactivities.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.austinactivities.Activity;
import com.austinactivities.Interests;
import com.austinactivities.RecommendedActivities;
import com.austinactivities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.apps.util.Console.*;

public class SpAustintaneousApp {
    private final Prompter prompter  = new Prompter(new Scanner(System.in));
    private  User user;
    private ArrayList<Interests> interestList = new ArrayList<>();

    public void execute() {
        welcomeScreen();
        promptForNewOrReturningUser();      //prompt screen asking if new or returning user
        mainMenu();
        goodbyeScreen();
    }
    //prompts for new or returning user - if new calls createAccount(), if old calls signIn()
    public void promptForNewOrReturningUser(){
        clear();

        boolean done = false;

        while (!done) {
            System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in\n");
            System.out.println();
            System.out.println();

            String input = prompter.prompt("Enter your choice: ");
            int choice = Integer.parseInt(input.trim());
            switch (choice) {
                case 1 :
                    createAccount();
                    done = true;
                    break;
                case 2 :
                    signIn();
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    public void createAccount() {
        clear();
        String input = prompter.prompt("Enter username: ");
        user = new User(input.trim());
    }

    private void signIn() {
        //TODO
        clear();
        System.out.println("Signing in...");
    }

    public void mainMenu() {
        clear();

        boolean done = false;

        while (!done) {
            System.out.println("SpAUSTINtaneous Menu:");
            System.out.println("1.Select Interest Categories");
            System.out.println("2.Generate a Recommendation List");
            System.out.println("3.Exit");
            System.out.println();
            System.out.println();

            String input = prompter.prompt("Enter your choice: ");
            int choice = Integer.parseInt(input.trim());
            switch (choice) {
                case 1 :
                    selectInterestCategories();
                    break;
                case 2 :
                    generateRecommendationList();
                    break;
                case 3  :
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();

        }
    }

    public void generateRecommendationList() {
        clear();
        //checks if user has actual populated interest list first
        if (user.getInterestList().isEmpty()|| user.getInterestList()==null){
            System.out.println("Your current interest list is empty.");
            System.out.println("Unable to generate recommendation list.");
            System.out.println("Please select interest categories first");
            returningToMainMenu();
        }
        else{
            List<Activity> generatedList = RecommendedActivities.generateActivityList(user.getInterestList());
            printActivityList(generatedList);

            //redirects user to main menu after they input [Enter]
            boolean done = false;
            while (!done) {
                String input = prompter.prompt("Press [Enter] when done viewing and you will be redirected back to the main menu. ");
                if (!input.trim().isEmpty()) {
                    System.out.println("Incorrect input");
                }
                else {
                    done = true;
                }

            }
        }
        clear();
        returningToMainMenu();
    }

    private void printActivityList(List<Activity> activityList) {
        System.out.println("Based on your interests, your recommended activity list is as follows:");
        for (Activity activity : activityList) {
            System.out.println(activity);
        }
    }

    public void selectInterestCategories() {
        clear();
        displayInterestsMenu();
        System.out.println();

        boolean done = false;
        while (!done) {
            String input = prompter.prompt("Enter the number corresponding to your choice, or press [Enter] when done: ");
            if(!input.trim().isEmpty()){
                int i = Integer.parseInt(input.trim());
                if (i >= 0 && i < Interests.values().length) {
                    //selecting the chosen input interest from user in int form and grabbing the corresponding enum
                    Interests selectedInterest = Interests.values()[i];
                    user.addInterest(selectedInterest);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
            else{
                done=true;
                System.out.println("Interest selection is done.");
            }
        }
        returningToMainMenu();
    }

    public static void displayInterestsMenu() {
        System.out.println("List of Interest Categories:");
        for (Interests interest : Interests.values()) {
            System.out.println(interest.ordinal() + " - " + interest);
        }
    }

    private void welcomeScreen(){
        try {
            clear();
            String welcome = Files.readString(Path.of("resources/welcomescreen.txt"));
            System.out.println("\n" + welcome + "\n");
            pause(3000);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void returningToMainMenu() {
        String text = "... Returning to main menu ...";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Console.pause(100);
        }
        pause(2000);
        clear();
    }

    private void goodbyeScreen() {
        clear();
        String text = "T H A N K   Y O U !";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Console.pause(100);
        }
        pause(2000);
        clear();
    }
}