package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class todo_groupsTest {
    protected static final String BASE_URL = "https://api.ibrohhm.com";
    protected static final String AUTH_TOKEN = "Bearer ya29.a0AeDClZC8nYhUyVJYCUao0K6jvSVUTsrswntLP8ec1xl-jLBXLDbP67xotP4UMWFLNo9lw329kbc_-qAE1RnF1FZNsi1nNGUHKrxMo-WA2jRNwvPh6c5LMtleWnnRpUV2CsR7z-bPzztd_k638WGnDmAesWXNb9bl7-b2a9zVaCgYKARISARISFQHGX2MisUvGiCN32M1l6eDza4NxQg0175";
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

    @Test
    public void testCreateTodo() {
        // Membuat payload untuk request
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "tes tiket"); // Ganti dengan ID grup yang valid
        payload.put("note", "task baru");  // Task baru

        // Mengirim POST request ke endpoint /todos
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Menyertakan token autentikasi
                .header("Content-Type", "application/json") // Menentukan format payload
                .body(payload) // Menambahkan payload ke body request
                .when()
                .post("/todo-groups");  // Endpoint untuk membuat todo

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Memvalidasi hasil sesuai dengan expected result
        assert response.getStatusCode() == 200 : "Expected status code 200, but got " + response.getStatusCode();
        assert response.jsonPath().getString("data.name").equals("tes tiket") : "tes mismatch!";
        assert response.jsonPath().getString("data.note").equals("task baru") : "Note mismatch!";
    }

    @Test
    public void testCreateTodoInvalidPayload() { // ini udah dicoba tapi payload yang kosong bisa, yang invalid tidak jelas seperti apa :)
        // Membuat payload invalid (data kosong)
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", ""); // Field "name" kosong
        payload.put("note", ""); // Field "note" kosong

        // Mengirim POST request ke endpoint /todo-groups dengan payload invalid
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Menyertakan token autentikasi
                .header("Content-Type", "application/json") // Menentukan format payload
                .body(payload) // Menambahkan payload ke body request
                .when()
                .post("/todo-groups");  // Endpoint untuk membuat todo group

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Memvalidasi hasil sesuai dengan expected result
        assert response.getStatusCode() == 400 || response.getStatusCode() == 422 :
                "Expected status code 400 or 422, but got " + response.getStatusCode();
    }

    @Test
    public void testGetTodoById() {
        // Mengirim GET request ke endpoint /todo-groups/{ticketId}
        Response getResponse = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Token autentikasi
                .when()
                .get("/todo-groups/11");  // Endpoint untuk mendapatkan tiket berdasarkan ID

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + getResponse.getStatusCode());
        System.out.println("Response Body: " + getResponse.asString());

        // Memastikan respons status 200
        assert getResponse.getStatusCode() == 200 :
                "Expected status code 200, but got " + getResponse.getStatusCode();

    }

    @Test
    public void testGetNotExistTodoById() {
        // Mengirim GET request ke endpoint /todo-groups/{ticketId}
        Response getResponse = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Token autentikasi
                .when()
                .get("/todo-groups/18");  // Endpoint untuk mendapatkan tiket berdasarkan ID

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + getResponse.getStatusCode());
        System.out.println("Response Body: " + getResponse.asString());

        // Memastikan respons status 200
        assert getResponse.getStatusCode() == 404 :
                "Expected status code 404, but got " + getResponse.getStatusCode();

    }

    @Test
    public void testPutTodo() {
        // Membuat payload untuk request
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "tes tiket update"); // Ganti dengan ID grup yang valid
        payload.put("note", "task baru");  // Task baru

        // Mengirim POST request ke endpoint /todos
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Menyertakan token autentikasi
                .header("Content-Type", "application/json") // Menentukan format payload
                .body(payload) // Menambahkan payload ke body request
                .when()
                .patch("/todo-groups/15");  // Endpoint untuk membuat todo

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Memvalidasi hasil sesuai dengan expected result
        assert response.getStatusCode() == 200 : "Expected status code 200, but got " + response.getStatusCode();
    }

    @Test
    public void testPutNotExistTodo() {
        // Membuat payload untuk request
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "tes tiket update"); // Ganti dengan ID grup yang valid
        payload.put("note", "task baru");  // Task baru

        // Mengirim POST request ke endpoint /todos
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Menyertakan token autentikasi
                .header("Content-Type", "application/json") // Menentukan format payload
                .body(payload) // Menambahkan payload ke body request
                .when()
                .patch("/todo-groups/17");  // Endpoint untuk membuat todo

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Memvalidasi hasil sesuai dengan expected result
        assert response.getStatusCode() == 404 : "Expected status code 404, but got " + response.getStatusCode();
    }

    @Test
    public void testDeleteTodo() {
        // Mengirim POST request ke endpoint /todos
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Menyertakan token autentikasi
                .when()
                .delete("/todo-groups/15");  // Endpoint untuk membuat todo

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Memvalidasi hasil sesuai dengan expected result
        assert response.getStatusCode() == 200 : "Expected status code 200, but got " + response.getStatusCode();
    }

    @Test
    public void testDeleteInvalidTodo() {

        // Mengirim POST request ke endpoint /todos
        Response response = RestAssured
                .given()
                .header("Authorization", AUTH_TOKEN)  // Menyertakan token autentikasi
                .when()
                .delete("/todo-groups/15");  // Endpoint untuk membuat todo

        // Menampilkan status dan respons body untuk debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Memvalidasi hasil sesuai dengan expected result
        assert response.getStatusCode() == 404 : "Expected status code 404, but got " + response.getStatusCode();
    }
}



