import com.codeborne.selenide.Condition;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DemoWebShopTests {

    @Test
    void addToCartTest() {
        Response response =
                given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&product_attribute_72_8_30=93&" +
                        "product_attribute_72_8_30=94&product_attribute_72_8_30=95&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().response();

        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());
    }

    @Test
    void addToCartWithCookieTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&product_attribute_72_8_30=93&" +
                                "product_attribute_72_8_30=94&product_attribute_72_8_30=95&addtocart_72.EnteredQuantity=1")
                        .cookie("Nop.customer=a4b2346f-cf3b-4d21-b7a2-9239fef03c52;")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                       // .body("updatetopcartsectionhtml", is("(1)"))
                        .extract().response();

        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());
    }

    @Test
    void addToWishlistTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("addtocart_22.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/22/2")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                        .extract().response();

        System.out.println(response.asString());

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");

        getWebDriver().manage().addCookie(
                new Cookie("Nop.customer", "e25e9ee7-c6e0-4c2e-9c1d-b06d2f0ef522"));
        open("http://demowebshop.tricentis.com/health");
        $(".wishlist-qty").shouldHave(Condition.text("(1)"));

    }
}
