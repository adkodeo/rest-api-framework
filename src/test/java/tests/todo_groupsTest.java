package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class todo_groupsTest {
    protected static final String BASE_URL = "https://api.ibrohhm.com";
    protected static final String AUTH_TOKEN = "Bearer ya29.a0AeDClZCrfsyk1OAWP1excYTiz-rg9UJnzNxGEuM2hWRNN-WZygyrJETRVKqGHmPKXdlPxwuAdzYmaqo6r-SJdGuSGdFSXLGWQ7gMdAocBcr9-KzWjG83HtFcNgNckMbxlEFHx2lVoNbXqZjKwTpdhCyLW356b-l8mlNSI94FaCgYKAQUSARISFQHGX2MiijywTCTsgnF_EgWwpOhteA0175";

    @BeforeClass
    public void setup() {
        // Set the base URI for the API
        RestAssured.baseURI = BASE_URL;

    }

    @Test
    public void testGetEndpoint() {
        // Send GET request to the tick-list page with the Authorization token
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Set Authorization header with Bearer token

                .when()
                .get("/todo-groups");  // Use the correct endpoint

        // Print the response to verify content
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Example assertion to verify status code (optional)
        assert response.getStatusCode() == 200 : "Expected status code 200, but got " + response.getStatusCode();
    }
}
