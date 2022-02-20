import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class RegresTests {

    @Test
    void userNotFound() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void createUser() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void createJobWithoutName() {

        given()
                .contentType(JSON)
                .body("{\"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("error", nullValue());
    }

    @Test
    void updateUserPut() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"zion resident\"}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    void updateUserPatch() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\"," +
                        "\"job\": \"lion\"}")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("job", is("lion"));
    }

    @Test
    void deleteUser() {
        given()
                .contentType(JSON)
                .body("")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

}
