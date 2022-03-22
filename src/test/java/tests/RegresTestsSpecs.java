package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.Specs.*;

public class RegresTestsSpecs {

    @Test
    void createUser() {
        given()
                .spec(requestUser)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec201);
    }

    @Test
    void createJobWithoutName() {
        given()
                .spec(requestCreateJobWithoutName)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec201);
    }

    @Test
    void updateUserPut() {
        given()
                .spec(requestUser)
                .when()
                .put("users/2")
                .then()
                .spec(responseSpec200);
    }

    @Test
    void updateUserPatch() {
        given()
                .contentType(JSON)
                .spec(requestUser)
                .when()
                .patch("users/2")
                .then()
                .spec(responseSpec200);
    }

}
