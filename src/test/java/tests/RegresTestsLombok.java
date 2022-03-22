package tests;

import lombok.LombokUser;
import models.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.*;


public class RegresTestsLombok {


    @Test
    void createUser() {
        LombokUser data = given()
                        .spec(requestUser)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec201)
                        .log().body()
                        .extract().as(LombokUser.class);

        assertEquals("morpheus", data.getName());
        assertEquals("zion resident", data.getJob());
    }

    @Test
    void createJobWithoutName() {
        LombokUser data = given()
                .spec(requestCreateJobWithoutName)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec201)
                .log().body()
                .extract().as(LombokUser.class);

        assertEquals("leader", data.getJob());
    }

    @Test
    void updateUserPut() {
        LombokUser data = given()
                .spec(requestUser)
                .when()
                .put("users/2")
                .then()
                .spec(responseSpec200)
                .log().body()
                .extract().as(LombokUser.class);

        assertEquals("morpheus", data.getName());
        assertEquals("zion resident", data.getJob());
    }

    @Test
    void updateUserPatch() {
        LombokUser data = given()
                .contentType(JSON)
                .spec(requestUser)
                .when()
                .patch("users/2")
                .then()
                .spec(responseSpec200)
                .log().body()
                .extract().as(LombokUser.class);

        assertEquals("morpheus", data.getName());
        assertEquals("zion resident", data.getJob());
    }

}
