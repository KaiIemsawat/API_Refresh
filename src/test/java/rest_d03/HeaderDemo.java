package rest_d03;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeaderDemo {

    @Test
    void testHeaders() {
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Server", "gws")
                .header("Content-Encoding", "gzip")
                .log().all();
    }

    @Test
    void getHeaders() {
        Response res = given()
                .when()
                .get("https://www.google.com");

        // Single header info
//        String contentType = res.getHeader("Content-Type");
//        System.out.println(contentType);

        // All header
        Headers headers = res.getHeaders();
        for (Header h : headers) {
            System.out.println(h.getName() + " : " + h.getValue());
        }


    }
}
