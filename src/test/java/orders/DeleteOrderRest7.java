package orders;


import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import setUp.SetUp;

public class DeleteOrderRest7 {

    SetUp setUp = new SetUp();


    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }



    @Test
    void TC1_delete_existing_order_with_authentication(){

        given()
                .spec(setUp.getRequestSpec())
                .pathParam("orderId","rjarfp-JKQol9b7y24k07").
                delete("/orders/{orderId}").
                then().statusCode(204).
                log().all();
    }


    @Test
    void TC2_delete_existing_order_without_authentication(){

        given()
                .spec(setUp.getRequestSpecWithoutAuth())
                .pathParam("orderId","vZRvLiBVluBx_e9f2yMmn").
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
