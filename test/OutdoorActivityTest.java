import com.austinactivities.Activity;
import com.austinactivities.Interests;
import com.austinactivities.OutdoorActivity;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class OutdoorActivityTest {
    public static void main(String[] args) {
        List<Interests> activity1Interest = List.of(Interests.Water_Activity, Interests.Hiking_Or_Parks);
        Activity activity1 = new OutdoorActivity("Barton Springs", "Natural Spring, great for cooling off and swimming",
                "Barton Springs, Barton Springs Rd, Austin, TX 78704", activity1Interest);

        System.out.println(activity1);

        // read to object creation
        try {
            File myObj = new File("./resources/activities-examples.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("printing data here");
                System.out.println(data);
            }
            myReader.close();

        } catch (Exception e) {
            System.out.println("Error occurred");
            e.printStackTrace();

        }

    }
}
