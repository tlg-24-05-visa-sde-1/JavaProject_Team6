package com.austinactivities.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.apps.util.Console.*;

public class SpAustintaneousApp {

    public void execute() {
        welcomeScreen();
        //promptForNewOrOldUser();

        //String user = promptForNewOrReturningUser();      //prompt screen asking if new or returning user
        //userLoginScreen();
        //showChoices();                                     //Choice Screen 1)Returning - dump recs/view recs  2)NEW user - shows choices of categories and asks to select from interests
        //showBoard();
        //updateBoard();      //will update
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