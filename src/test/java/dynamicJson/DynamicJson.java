package dynamicJson;

import ReUsableMthods.ReUsableMethod;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        String resp=given().header("Content-type","application/json")
                .body(Payload.addBook(isbn,aisle)).
                when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethod.rowToJson(resp);
        String id = js.get("ID");
        System.out.println(id);

        // delete Book

    }
    @DataProvider(name="BooksData")
    public Object[][] getData(){
        //array=collection of elements
        //multidimensional array= collection of array
    return new Object[][]{{"ifdj","4523"},{"sdfsdf","4443"},{"sdfgdf","85649"}};
    }
}
