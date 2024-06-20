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
    private User user;
    private List<Interests> interestList = new ArrayList<>();
    private HashSet<User> users = loadUsers();

    public void execute() {
        welcomeScreen();
        promptForNewOrReturningUser();      //prompt screen asking if new or returning user
        mainMenu();
        goodbyeScreen();
    }

    //*** MENU METHODS ***

    //prompts for new or returning user - if new calls createAccount(), if old calls signIn()
    private void promptForNewOrReturningUser() {
        boolean done = false;
        System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----");
        System.out.println("1. Create an account");
        System.out.println("2. Sign in\n");
        while (!done) {
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
                        if (user != null) {
                            done = true;
                        }
                    }
                    default -> {
                        System.out.println("Invalid choice. Please try again.\n");
                        pause(350);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try a numerical digit.\n");
                pause(350);
            }
            System.out.println();
        }
    }

    private void mainMenu() {
        clear();
        boolean done = false;
        while (!done) {
        System.out.println("-----       SpAUSTINtaneous Menu:       -----\n");
        System.out.println("1. Select Interest Categories");
        System.out.println("2. Generate a Recommendation List");
        System.out.println("3. View previously saved lists");
        System.out.println("4. Exit\n");
        System.out.println("-----  SELECT FROM THE OPTIONS ABOVE    -----\n");
            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1 -> selectInterestCategories();
                    case 2 -> generateRecommendationList();
                    case 3 -> savedListsMenu();
                    case 4 -> done = true;
                    default -> {
                        System.out.println("Invalid choice. Please try again.\n");
                        pause(350);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.\n");
                pause(350);
            }
            System.out.println();
        }
    }

    private void selectInterestCategories() {
        clear();
        displayInterestsMenu();
        System.out.println();

        boolean done = false;
        while (!done) {
            String input = prompter.prompt("Enter the number corresponding to your choice, or press [Enter] when done: ");
            if (!input.trim().isEmpty()) {
                try {
                    int i = Integer.parseInt(input.trim());
                    if (i >= 0 && i < Interests.values().length) {
                        //selecting the chosen input interest from user in int form and grabbing the corresponding enum
                        Interests selectedInterest = Interests.values()[i];
                        user.addInterest(selectedInterest);
                        System.out.println(" ");
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                        pause(350);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a numerical digit.");
                    pause(350);
                }
            } else {
                done = true;
                System.out.println("\nInterest selection is done.\n");
            }
        }
        returningToMainMenu();
    }

    private static void displayInterestsMenu() {
        System.out.println("-----    LIST OF INTEREST CATEGORIES:   -----\n");
        for (Interests interest : Interests.values()) {
            System.out.println(interest.ordinal() + " - " + interest);
        }
        System.out.println("\n-----  SELECT FROM THE OPTIONS ABOVE    -----\n");
    }

    private void generateRecommendationList() {

        boolean continueGenerating = true;

        while (continueGenerating) {
            clear();
            //checks if user has actual populated interest list first
            if (user.getInterestList() == null || user.getInterestList().isEmpty()) {
                System.out.println("\nYour current interest list is empty.");
                System.out.println("Unable to generate recommendation list.");
                System.out.println("Please select interest categories first\n");
                returningToMainMenu();
                return;
            } else {
                //Generating random recommended list associated with users interest
                RecommendedActivities rc = new RecommendedActivities();
                List<Activity> generatedList1 = rc.generateActivityList(user.getInterestList());
                List<Activity> generatedList = removeDuplicates(generatedList1);

                //printing list and menu choices
                System.out.println("----- RANDOMLY GENERATED ACTIVITY LIST: -----\n");
                printGeneratedList(generatedList);
                System.out.println("\n");
                System.out.println("----- SELECT FROM THE FOLLOWING OPTIONS -----\n");
                System.out.println("1. Save Generated Activity List");
                System.out.println("2. Generate another Activity List");
                System.out.println("3. Return back to MAIN MENU \n");

                //Asks the user if they would like to save the list Y/N [Enter]
                boolean done = false;
                while (!done) {
                    String input = prompter.prompt("Enter your choice: ");
                    //accounting for non-numerical digit
                    try {
                        int choice = Integer.parseInt(input.trim());

                        switch (choice) {
                            case 1 -> {
                                user.saveActivityList(new ArrayList<>(generatedList));
                                System.out.print("Your generated activity list has been saved.");
                                done = true; // Exit the loop after saving
                            }
                            case 2 -> {
                                clear();
                                done = true; // Exit the inner loop to generate a new list
                            }
                            case 3 -> {
                                continueGenerating = false; // Exit the outer loop and return to main menu
                                done = true;
                            }
                            default -> {
                                System.out.println("Invalid choice. Please try again.");
                                pause(350);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numerical digit.");
                        pause(350);
                    }
                    System.out.println();
                }
            }
        }
        returningToMainMenu();
    }

    private void printGeneratedList(List<Activity> generatedList) {
        for (Activity activity : generatedList) {
            System.out.println("-> " + activity.toString() + " \n");
        }
    }

    //Remove duplicates from the generated Activitylist ALSO LIMITING THE RETURNED LIST TO 3
    public static List<Activity> removeDuplicates(List<Activity> activities) {
        // Creating a HashSet to track names we've already encountered
        HashSet<String> seenNames = new HashSet<>();
        ArrayList<Activity> uniqueActivities = new ArrayList<>();

        // Iterating through the original list of activities
        for (Activity activity : activities) {
            // Check if the name has already been seen
            if (!seenNames.contains(activity.getName())) {
                // If not, add the name to the set and the activity to the result list
                seenNames.add(activity.getName());
                uniqueActivities.add(activity);
            }
            // Stop adding if we've reached 3 activities (just for presentation purposes)
            if (uniqueActivities.size() == 3) {
                break;
            }
        }
        return uniqueActivities;
    }

    private void savedListsMenu() {

        boolean done = false;
        while (!done) {
            clear();
        System.out.println("-----           SAVED LIST MENU         -----\n");
        System.out.println("1. View saved interest categories");
        System.out.println("2. View previously saved recommended activities list");
        System.out.println("3. Return back to MAIN MENU\n");
        System.out.println("\n-----  SELECT FROM THE OPTIONS ABOVE    -----\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1 -> interestCategoriesMenu();
                    case 2 -> savedRecommendationsListMenu();
                    case 3 -> done = true;
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        pause(350);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
                pause(350);
            }
            System.out.println();
        }
        returningToPreviousMenu();
    }

    private void savedRecommendationsListMenu() {

        boolean done = false;
        while (!done) {
            clear();
        System.out.println("-----YOUR PREVIOUSLY SAVED RECOMMENDED ACTIVITIES:-----\n");
        printRecommendedActivities();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\n--------------------------------------------------------\n");
        System.out.println("1. Add/Delete recommended activities from saved list(... COMING SOON..)");
        System.out.println("2. Return back to PREVIOUS MENU\n");
        System.out.println("\n-----  SELECT FROM THE OPTIONS ABOVE    -----\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1 -> System.out.println("... this function is coming soon!...");
                    case 2 -> done = true;
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        pause(350);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
                pause(350);
            }
            System.out.println();
        }
        returningToPreviousMenu();
    }

    private void printRecommendedActivities() {
        if (user.getActivityList() == null || user.getActivityList().isEmpty()) {
            System.out.println("You currently don't have any saved recommended activities.\n");
        } else {
            List<Activity> userList = user.getActivityList();
            for (Activity a : userList) {
                System.out.println(a.getName() + " - " + a.getDescription());
            }
        }
    }

    private void interestCategoriesMenu() {

        boolean done = false;
        while (!done) {
            clear();
        System.out.println("-----YOUR PREVIOUSLY SAVED INTEREST CATEGORIES:-----\n");
        printInterestCategories();
        System.out.println("\n----------------------------------------------------");
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("1. Add/Delete interest categories from saved list(... COMING SOON..)");
        System.out.println("2. Return back to PREVIOUS MENU\n");
        System.out.println("\n-----  SELECT FROM THE OPTIONS ABOVE    -----\n");

            String input = prompter.prompt("Enter your choice: ");
            //accounting for non-numerical digit
            try {
                int choice = Integer.parseInt(input.trim());

                switch (choice) {
                    case 1 -> System.out.println("... this function is coming soon!...");
                    case 2 -> done = true;
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        pause(350);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numerical digit.");
                pause(350);
            }
            System.out.println();
        }
        returningToPreviousMenu();
    }

    private void printInterestCategories() {
        if (user.getInterestList() == null || user.getInterestList().isEmpty()) {
            System.out.println("You currently don't have any saved interest categories.\n");
        } else {
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
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))) {
            out.writeObject(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //loads the HashSet users
    private static HashSet<User> loadUsers() {
        HashSet<User> users = null;

        if (Files.exists(Path.of(DATA_FILE_PATH))) { //file exists, read in the file
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH))) {
                users = (HashSet<User>) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // create a new users Hashset
            users = new HashSet<>();
        }

        return users;
    }

    //Creates a new account for current user
    private void createAccount() {
        String username;
        while (true) {
            username = prompter.prompt("Enter username: ").trim();
            if (checkUsername(username)) {
                System.out.println("Error: Username is already taken. Please try again.");
            } else {
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
        String input = prompter.prompt("Enter username: ");
        String trimmedInput = input.trim();

        //find user
        for (User userInList : users) {
            if (trimmedInput.equalsIgnoreCase(userInList.getUserName())) {
                user = userInList;
                break;
            }
        }
        //if traversing the saved users and user not found
        if (user == null) {
            System.out.println("Invalid username. Please try again.");
        }
    }

    //helper method for signIn() method
    private boolean checkUsername(String username) {
        for (User u : users) {
            if (username.equalsIgnoreCase(u.getUserName())) {
                return true;
            }
        }
        return false;
    }

    //**** TRANSITION SCREENS ****

    private void welcomeScreen() {
        try {
            clear();
            String welcome = Files.readString(Path.of("resources/welcomescreen.txt"));
            System.out.println("\n" + welcome + "\n");
            pause(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returningToMainMenu() {
        String text = "... Returning to main menu ...";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Console.pause(10);
        }
        pause(350);
        clear();
    }

    private void returningToPreviousMenu() {
        String text = "... Returning to previous menu ...";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Console.pause(10);
        }
        pause(350);
        clear();
    }

    private void goodbyeScreen() {
        save(users);
        clear();
        String text = "T H A N K   Y O U !";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Console.pause(25);
        }
        pause(2000);
        clear();
    }
}