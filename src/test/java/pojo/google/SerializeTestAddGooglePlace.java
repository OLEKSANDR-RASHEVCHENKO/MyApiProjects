package pojo.google;

import io.restassured.RestAssured;
import io.restassured.response.Response;

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

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response res=given().log().all().queryParam("key","qaclick1234")
                .body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        String responseAsString = res.asString();
        System.out.println(responseAsString);

    }
}
