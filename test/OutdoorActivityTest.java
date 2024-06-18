import com.austinactivities.Activity;
import com.austinactivities.Interests;
import com.austinactivities.LoadActivitiesAndCreate;
import com.austinactivities.OutdoorActivity;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class OutdoorActivityTest {
    public static void main(String[] args) {
        // testing new load
        LoadActivitiesAndCreate start = new LoadActivitiesAndCreate();
        start.loadActivitiesFromTxt("./resources/activities-examples.txt");
        System.out.println(start.getActivities());
        System.out.println();
        start.getActivitiesOnInterests("Restaurants");

    }
}
