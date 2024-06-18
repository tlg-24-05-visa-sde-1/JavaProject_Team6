package com.austinactivities.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.austinactivities.Interests;
import com.austinactivities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    private void createAccount() {
        clear();
        String input = prompter.prompt("Enter username: ");
        user = new User(input.trim());
    }

    private void signIn() {
        //TODO
        clear();
        System.out.println("Signing in...");
    }

    private void mainMenu() {
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
        //TODO
    }

    public void selectInterestCategories() {
        clear();
        boolean done = false;

        while (!done) {
            System.out.println("List of Interest Categories:");
            System.out.println("1.Water Activity");
            System.out.println("...");
            System.out.println("5.Exit");
            System.out.println();
            System.out.println();

            String input = prompter.prompt("Enter your choice: ");
            int choice = Integer.parseInt(input.trim());
            Interests i = choicetoInterests(choice);
            switch (choice) {
                case 1:
                    user.addInterest(i);
                    break;
                case 5 :
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        }
    }

    private Interests choicetoInterests(int choice) {
        Interests interest = null;
        //TODO - conversion of choice to enum activity
        System.out.println("Water Activity conversion");
        return Interests.Water_Activity;
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
}