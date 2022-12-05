package orders;


import org.junit.After;
import org.junit.Before;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import setUp.SetUp;

public class GetOrdersRest4 {

    SetUp setUp = new SetUp();

    @Before
    void setUp(){
        System.out.println("SetUp");
    }


    @Test
    void TC1_get_all_orders(){
        given().spec(setUp.getRequestSpec()).
                get("/orders").
                then().
                statusCode(200).
                log().all().
                body("[0].id", equalTo("cCP47zVMdJqpWcYpFKeuQ"));
                //body(".length", equalTo(5));
    }

    @Test
    void TC2_get_all_orders_without_authentication(){
        given().spec(setUp.getRequestSpecWithoutAuth()).
                get("/orders").
                then().
                statusCode(401).
                log().all().
                body("error", equalTo("Missing Authorization header."));
    }














    @After
    void tearDown(){
        System.out.println("TearDown");
    }
}
