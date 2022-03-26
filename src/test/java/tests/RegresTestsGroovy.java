package tests;

import lombok.LombokUser;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static specs.Specs.*;


public class RegresTestsGroovy {


    @Test
    void userFound() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/")
                .then()
                .log().body()
                .body("data.findAll{it.first_name}.first_name.flatten()", hasItem("Janet"));
    }

    @Test
    void createUser() {
                 given()
                .spec(requestUser)
                .when()
                .post("/users")
                .then()
                .log().body()
                .body("data.findAll.flatten()", is(not(empty())));
    }

}
