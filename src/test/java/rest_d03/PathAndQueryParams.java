package rest_d03;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class PathAndQueryParams {

    @Test
    void testQueryAndPathParams() {
        given()
                .pathParam("path", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get("https://reqres.in/api/{path}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
