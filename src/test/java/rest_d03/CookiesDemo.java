package rest_d03;

import io.restassured.response.Response;

import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;


public class CookiesDemo {

    @Test
    void testCookies() {
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .cookie("AEC", "AVYB7cpEFGf0GenOgTNN-3urGvZTgDrGbvnT4ejVldxptOvRDXHCmi5tVA")
                .log().all();
    }

    @Test
    void getCookiesInfo() {
        Response res =given()
                .when()
                .get("https://www.google.com");

        String cookieValue = res.getCookie("AEC"); // Get single cookie
        Map<String, String> cookieValues = res.cookies();
        System.out.println(cookieValue);
        for (String key : cookieValues.keySet()) {
            System.out.println(key + " : " + cookieValues.get(key));
        }

    }
}
