package googleCopiWithSpecBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTestAddGooglePlace {
    public static void main(String[] args) {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("9,Dresdner Strasse");
        addPlace.setLanguage("Deutch");
        addPlace.setPhone_number("49272390239");
        addPlace.setWebsite("//hhhtp.sdflksdkfjljsdfj.com");
        addPlace.setName("Alex");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);

        Location location = new Location();
        location.setLat(-234.23423);
        location.setLng(34.334);
        addPlace.setLocation(location);


        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification response1 =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();



        RequestSpecification res=given().spec(requestSpecification).log().all()
                .body(addPlace);
                Response response=res.when().post("/maps/api/place/add/json")
                .then().spec(response1).extract().response();
        String responseAsString = response.asString();
        System.out.println(responseAsString);

    }
}
