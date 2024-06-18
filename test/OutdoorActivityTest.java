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
        List<Activity> activityList = start.getActivities();
        start.dump(activityList);
        System.out.println();
        start.getActivitiesOnInterests("Restaurants");

    }
}
