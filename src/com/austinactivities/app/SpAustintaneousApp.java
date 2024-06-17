package com.austinactivities.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.austinactivities.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static com.apps.util.Console.*;

public class SpAustintaneousApp {
    private final Prompter prompter  = new Prompter(new Scanner(System.in));
    private  User user;
    private boolean returningUser = false;

    public void execute() {
        welcomeScreen();
        promptForNewOrReturningUser();      //prompt screen asking if new or returning user
        mainMenu();
        goodbyeScreen();
    }
    private void promptForNewOrReturningUser(){
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
        //TODO
        clear();
        System.out.println("Creating a new account...");
    }
    private void signIn() {
        //TODO
        clear();
        System.out.println("Sign in...");
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

    private void generateRecommendationList() {
        clear();
        System.out.println("Generating Recommendation List");
    }

    private void selectInterestCategories() {
        clear();
        System.out.println("Generating Interest Categories");
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