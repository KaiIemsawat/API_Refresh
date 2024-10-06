package rest_d02;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreatingPostRequestBody {

    String id;

    @Test
    void testPostUsingHashMap() {
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

    @Test(dependsOnMethods = {"testPostUsingHashMap"}, priority = 1)
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

    @Test(dependsOnMethods = {"testPostUsingHashMap"}, priority = 2)
    void testDelete() {
        given()
                .when().delete("http://localhost:3000/students/" + id)
                .then().statusCode(200)
                .log().all();
    }
}
