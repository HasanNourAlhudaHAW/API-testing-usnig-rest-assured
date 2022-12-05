package orders;

import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import setUp.SetUp;

public class GetSingleOrderRest5 {

    SetUp setUp = new SetUp();

    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }


    @Test
    void TC1_get_existing_order_with_authentication(){
        given()
                .spec(setUp.getRequestSpec())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeuQ").
                get("/orders/{orderId}").
                then().statusCode(200).
                log().all().
                body("id", equalTo("cCP47zVMdJqpWcYpFKeuQ"));
    }

    @Test
    void TC2_get_inexisting_order_with_authentication(){
        given()
                .spec(setUp.getRequestSpec())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeu").
                get("/orders/{orderId}").
                then().statusCode(404).
                log().all().
                body("error", equalTo("No order with id cCP47zVMdJqpWcYpFKeu."));
    }

    @Test
    void TC3_get_existing_order_without_authentication(){
        given()
                .spec(setUp.getRequestSpecWithoutAuth())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeuQ").
                get("/orders/{orderId}").
                then().statusCode(401).
                log().all().
                body("error", equalTo("Missing Authorization header."));
    }
}
