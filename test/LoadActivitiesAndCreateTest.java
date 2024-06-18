import com.austinactivities.Activity;
import com.austinactivities.Interests;
import com.austinactivities.LoadActivitiesAndCreate;
import java.util.List;

public class LoadActivitiesAndCreateTest {
    public static void main(String[] args) {
        // testing new load
        LoadActivitiesAndCreate start = new LoadActivitiesAndCreate();
        start.loadActivitiesFromTxt("./resources/activities-examples.txt");
        List<Activity> activityList = start.getActivities();
        start.dump(activityList);
        System.out.println("Based on Interests");
        List<Activity> restaurantActivities = start.getActivitiesOnInterests(Interests.Restaurants);
        System.out.println(restaurantActivities);

    }
}
