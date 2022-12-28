package orders;


import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import setUp.SetUp;

public class DeleteOrder {

    SetUp setUp = new SetUp();


    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }



    @Test
    void TC1_delete_existing_order_with_authentication(){

        given()
                .spec(setUp.getRequestSpecWithAuth())
                .pathParam("orderId","8p9eQwF9LhaPeYGHIYxjw").
                delete("/orders/{orderId}").
                then().statusCode(204).
                log().all();
    }

    @Test
    void TC1_delete_inexisting_order_with_authentication(){

        given()
                .spec(setUp.getRequestSpecWithAuth())
                .pathParam("orderId","8p9eQwF9LhaPeYGHIYxjws").
                delete("/orders/{orderId}").
                then().statusCode(404).
                log().all();
    }

    @Test
    void TC2_delete_existing_order_without_authentication(){

        given()
                .spec(setUp.getRequestSpecWithoutAuth())
                .pathParam("orderId","8p9eQwF9LhaPeYGHIYxjw").
                delete("/orders/{orderId}").
                then().statusCode(401).
                log().all().
                        body("error", equalTo("Missing Authorization header."));
    }


    @AfterSuite
    void tearDown(){
        System.out.println("TearDown");
    }
}
