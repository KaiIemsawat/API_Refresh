package rest_d01;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {

    int id;
    String outputName;
    String inputName = "Zukkii";
    String updateName = "Titann";

    @Test
    void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    void createUser() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("name" , inputName);
        userData.put("job", "eat");

        id = given().contentType("application/json").body(userData)
                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//                .then().statusCode(201).log().all();
    }

    @Test(dependsOnMethods = {"createUser"}, priority = 3)
    void updateUser() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("name" , updateName);
        userData.put("job", "eat");

        outputName = given().contentType("application/json").body(userData)
                .when().put("https://reqres.in/api/users/" + id)
                .jsonPath().getString("name");
//                .then().statusCode(201).log().all();
        Assert.assertEquals(updateName, outputName);
    }

    @Test(dependsOnMethods = {"updateUser"}, priority = 4)
    void deleteUser() {
        given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
    }

}
