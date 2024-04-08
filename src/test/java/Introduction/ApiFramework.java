package Introduction;

import ReUsableMthods.ReUsableMethod;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiFramework {
    public static void main(String[] args) {
        //validate if Add Place Api is working as expected

        // given - all input details
        // when - Submit the Api - resource,http method
        //then - validate the response
        // see in postman this Request APITEST
// Add place --> Update place with new Address --> Get Place to validate if New Address is present
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParams("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);// for parsing JSON
        String placeId=jsonPath.getString("place_id");
        System.out.println(placeId);

        // Update Place
        String newAddress = "70 Summerr walk, AFRICA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));


        // Get Post

        String  getPlaceResponse=given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().asString();

        JsonPath jsonPath1= ReUsableMethod.rowToJson(getPlaceResponse); // Create class with rowToJson
        String actualAddress=jsonPath1.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(newAddress,actualAddress);


    }
}
