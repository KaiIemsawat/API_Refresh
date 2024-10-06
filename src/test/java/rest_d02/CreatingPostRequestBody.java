package rest_d02;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreatingPostRequestBody {

    /* NOTE : run ->  json-server students.json  <- before testing */

    String id;

//    @Test
    void testPostUsingHashMap() { // Using HashMap
        HashMap data = new HashMap<>();
        data.put("name", "Stokii");
        data.put("location", "MtVernon");
        data.put("phone", "703-123-1234");

        String courseArr[] = {"C#", "Python"};

        data.put("courses", courseArr);

        id = given().contentType("application/json").body(data)
                .when().post("http://localhost:3000/students")
                .jsonPath().getString("id");
    }

    @Test
    void testPostUsingJsonLibrary() { // Using org.json library
        JSONObject data = new JSONObject();
        data.put("name", "Stokii");
        data.put("location", "MtVernon");
        data.put("phone", "703-123-1234");

        String courseArr[] = {"C#", "Python"};
        data.put("courses", courseArr);

        id = given().contentType("application/json").body(data.toString())
                .when().post("http://localhost:3000/students")
                .jsonPath().getString("id");
    }

    @Test(priority = 1)
    void testGetStudent() {
        given()
                .when().get("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("name", equalTo("Stokii"))
                .body("location", equalTo("MtVernon"))
                .body("phone", equalTo("703-123-1234"))
                .body("courses[0]", equalTo("C#"))
                .body("courses[1]", equalTo("Python"))
                .log().all();
    }

    @Test(priority = 2)
    void testDelete() {
        given()
                .when().delete("http://localhost:3000/students/" + id)
                .then().statusCode(200)
                .log().all();
    }


}
