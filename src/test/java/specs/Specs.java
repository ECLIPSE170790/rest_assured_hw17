package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;

public class Specs {

    static String uri = "https://reqres.in";
    static String path = "/api";

    public static RequestSpecification requestUser = with()
            .baseUri(uri)
            .basePath(path)
            .body("{\"name\": \"morpheus\"," +
                    "\"job\": \"zion resident\"}")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification requestCreateJobWithoutName = with()
            .baseUri(uri)
            .basePath(path)
            .body("{\"job\": \"leader\"}")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("job", is("zion resident"))
            .build();
}
