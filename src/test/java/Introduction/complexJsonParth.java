package Introduction;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParth {
    public static void main(String[] args) {


        JsonPath js = new JsonPath(Payload.coursePrice());
        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print purchaseAmount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // Print title from first Course
        String titleFromFirstCourse = js.get("courses[0].title");
        System.out.println(titleFromFirstCourse);

        //Print all course titles and their prices
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            int coursePrices = js.getInt("courses[" + i + "].price");
            System.out.println(courseTitles + "=" + coursePrices);
        }
        //Print no Of copies sold by RPA

        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            if (courseTitles.equalsIgnoreCase("RPA")){
                //copies sold
                int copies=js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }
        // Verify if Sum of All Course prices see in SUM VALIDATION CLASS
    }

}
