package Introduction;

import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumValidationOfCourses(){
        int sum = 0;
        JsonPath js = new JsonPath(Payload.coursePrice());
        int count = js.getInt("courses.size()");
        for (int i = 0;i<count;i++){
            int price=js.get("courses["+i+"].price");
            int copies=js.get("courses["+i+"].copies");
            int  amount = price * copies;
            System.out.println(amount);
            sum = sum + amount;
        }
        System.out.println(sum);
        int dashboardPurchaseAmount=js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,dashboardPurchaseAmount);
    }
}
