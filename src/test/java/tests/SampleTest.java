package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SampleTest {

    @BeforeClass
    public void setup() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetEndpoint() {
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1");
        System.out.println(response.asString());
    }
}
