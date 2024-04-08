package ReUsableMthods;

import io.restassured.path.json.JsonPath;

public class ReUsableMethod {

    public  static  JsonPath rowToJson(String response){
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
