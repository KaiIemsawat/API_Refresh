package rest_d02;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreatingPostRequestBody {

    @Test
    void testPostUsingHashMap() {
        HashMap data = new HashMap<>();
        data.put("name", "Stokii");
        data.put("location", "MtVernon");
        data.put("phone", "703-123-1234");

        String courseArr[] = {"C#", "Python"};

        data.put("courses", courseArr);

        given().contentType("application/json").body(data)
                .when().post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json")
                .body("name", equalTo("Stokii"))
                .body("location", equalTo("MtVernon"))
                .body("phone", equalTo("703-123-1234"))
                .body("courses[0]", equalTo("C#"))
                .body("courses[1]", equalTo("Python"))
                .log().all();
    }

    @Test
    void
}
