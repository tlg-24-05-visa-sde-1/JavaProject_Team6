package com.austinactivities.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.austinactivities.Activity;
import com.austinactivities.Interests;
import com.austinactivities.RecommendedActivities;
import com.austinactivities.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static com.apps.util.Console.*;

public class SpAustintaneousApp {
    private final Prompter prompter  = new Prompter(new Scanner(System.in));
    private  User user ;
    private ArrayList<Interests> interestList = new ArrayList<>();
    private HashSet<User> users = loadUsers();

    public void execute() {
        welcomeScreen();
        promptForNewOrReturningUser();      //prompt screen asking if new or returning user
        mainMenu();
        goodbyeScreen();
    }

    //*** MENU METHODS ***

    //prompts for new or returning user - if new calls createAccount(), if old calls signIn()
    public void promptForNewOrReturningUser(){
        clear();

        boolean done = false;

        while (!done) {
            System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in\n");

            String input = prompter.prompt("Enter your choice: ");

            //taking into account if the user puts in a non-numerical input
            try {
                int choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1 -> {
                        createAccount();
                        done = true;
                    }
                    case 2 -> {
                        signIn();
                        done = true;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try a numerical digit.");
            }
            System.out.println();
        }
    }

    public void mainMenu() {
        clear();

        boolean done = false;

        while (!done) {
            System.out.println("SpAUSTINtaneous Menu:");
            System.out.println("1. Select Interest Categories");
            System.out.println("2. Generate a Recommendation List");
            System.out.println("3. View previously saved lists");
            System.out.println("4. Exit\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1 -> selectInterestCategories();
                    case 2 -> generateRecommendationList();
                    case 3 -> savedListsMenu();
                    case 4 -> done = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
            }
            System.out.println();
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
                try {
                    int i = Integer.parseInt(input.trim());
                    if (i >= 0 && i < Interests.values().length) {
                        //selecting the chosen input interest from user in int form and grabbing the corresponding enum
                        Interests selectedInterest = Interests.values()[i];
                        user.addInterest(selectedInterest);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid choice. Please enter a numerical digit.");
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

    public void generateRecommendationList() {
        clear();
        //checks if user has actual populated interest list first
        if (user.getInterestList().isEmpty()||user.getInterestList()==null){
            System.out.println("Your current interest list is empty.");
            System.out.println("Unable to generate recommendation list.");
            System.out.println("Please select interest categories first");
            returningToMainMenu();
        }
        else{
            //Generating random recommended list associated with users interest
            RecommendedActivities rc = new RecommendedActivities();
            List<Activity> generatedList = rc.generateActivityList(user.getInterestList());

            //printing list and menu choices
            System.out.println("----- RANDOMLY GENERATED ACTIVITY LIST: -----");
            printGeneratedList(generatedList);
            System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
            System.out.println("1.Save Generated Activity List");
            System.out.println("2.Generate another Activity List");
            System.out.println("3.Return back to MAIN MENU \n");

            //Asks the user if they would like to save the list Y/N [Enter]
            boolean done = false;
            while (!done) {
                String input = prompter.prompt("Enter your choice: ");
                //accounting for non-numerical digit
                try {
                    int choice = Integer.parseInt(input.trim());

                    switch (choice) {
                        case 1-> user.saveActivityList((ArrayList<Activity>) generatedList);
                        case 2-> generateRecommendationList();
                        case 3 -> done = true;
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numerical digit.");
                }
                System.out.println();
            }
        }
        clear();
        returningToMainMenu();
    }

    private void printGeneratedList(List<Activity> generatedList) {
        for (Activity activity : generatedList) {
            System.out.println(activity.getName() + " - " + activity.getDescription());
        }
    }

    public void savedListsMenu() {
        clear();
        boolean done = false;
        while (!done) {
            System.out.println("-----           SAVED LIST MENU         -----");
            System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
            System.out.println("1.View saved interest categories");
            System.out.println("2.View previously saved recommendation list");
            System.out.println("3.Return back to MAIN MENU\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1-> interestCategoriesMenu();
                    case 2-> savedRecommendationsListMenu();
                    case 3 -> done = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
            }
            System.out.println();
        }
        returningToPreviousMenu();
    }

    private void savedRecommendationsListMenu() {
        clear();
        boolean done = false;
        while (!done) {
            System.out.println("-----YOUR PREVIOUSLY SAVED RECOMMENDED ACTIVITIES:-----\n");
            printRecommendedActivities();
            System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
            System.out.println("1. Add/Delete recommended activities from saved list(coming soon..)");
            System.out.println("2. Return back to MAIN MENU\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1-> interestCategoriesMenu();
                    case 2 -> done = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
            }
            System.out.println();
        }
        returningToPreviousMenu();
    }

    private void printRecommendedActivities() {
        if (user.getInterestList().isEmpty()||user.getInterestList()==null){
            System.out.println("You currently don't have any saved recommended activities.\n");
        }
        else {
            List<Activity> userList = user.getActivityList();
            for(Activity a : userList){
                System.out.println(a.getName()+" - "+a.getDescription());
            }
        }

    }

    private void interestCategoriesMenu() {
        clear();
        boolean done = false;
        while (!done) {
            System.out.println("-----YOUR PREVIOUSLY SAVED INTEREST CATEGORIES:-----\n");
            printInterestCategories();
            System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
            System.out.println("1. Add/Delete interest categories from saved list(coming soon..)");
            System.out.println("2. Return back to MAIN MENU\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1-> interestCategoriesMenu();
                    case 2 -> done = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
            }
            System.out.println();
        }
        returningToPreviousMenu();
    }

    private void printInterestCategories() {
        if (user.getInterestList().isEmpty()||user.getInterestList()==null){
            System.out.println("You currently don't have any saved interest categories.\n");
        }
        else {
            List<Interests> userList = user.getInterestList();
            System.out.println(userList);
        }
    }

    private void printActivityList(List<Activity> activityList) {
        System.out.println("Based on your interests, your recommended activity list is as follows:");
        for (Activity activity : activityList) {
            System.out.println(activity);
        }
    }


    //*** USER MANAGEMENT METHODS ***

    private static final String DATA_FILE_PATH = "resources/users.dat";

    //saves the users HashSet
    private static void save(HashSet<User> users) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))){
            out.writeObject(users);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //loads the HashSet users
    private static HashSet<User> loadUsers() {
        HashSet<User> users = null;

        if(Files.exists(Path.of(DATA_FILE_PATH))) {//file exists, read in the file
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH))) {
                users =(HashSet<User>) in.readObject();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{ // create a new users Hashset
            users = new HashSet<>();
        }

        return users;
    }

    //Creates a new account for current user
    public void createAccount() {
        clear();
        String username;
        //= prompter.prompt("Enter username: ");
        while(true){
            username = prompter.prompt("Enter username: ").trim();
            if(checkUsername(username)){
                System.out.println("Error: Username is already taken. Please try again.");
            }
            else{
                user = new User(username);
                users.add(user);
                System.out.println("Account created successfully for username: " + user.getUserName());
                break;
            }
        }
        save(users);
    }

    //Returning user can sign in
    private void signIn() {
        clear();
        String input = prompter.prompt("Enter username: ");
        String trimmedInput = input.trim();

        //find user
        for (User userInList : users) {
            if(trimmedInput.equalsIgnoreCase(userInList.getUserName())){
                user = userInList;
            }
        }
        //if traversing the saved users and user note found
        if(user == null){
            System.out.println("Invalid username. Please try again.");
        }
    }

    //helper method for signIn() method
    private boolean checkUsername(String username) {
        boolean found = false;
        for(User u: users){
            if(username.equalsIgnoreCase(u.getUserName())){
                found = true;
            }
        }
        return found;
    }



    //**** TRANSITION SCREENS ****

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

    public void returningToPreviousMenu() {
        String text = "... Returning to previous menu ...";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Console.pause(100);
        }
        pause(2000);
        clear();
    }

    private void goodbyeScreen() {
        save(users);
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