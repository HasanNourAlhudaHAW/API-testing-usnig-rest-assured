package orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.OrderInfo;
import org.testng.annotations.*;
import setUp.SetUp;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateOrderRest6 {

    SetUp setUp = new SetUp();
    OrderInfo orderInfo = new OrderInfo();

    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }


    @Test
    void TC1_update_existing_order_with_authentication() throws JsonProcessingException {
        String customerName = orderInfo.updateOrderCustomerName("Mustermann4");

        given()
                .spec(setUp.getRequestSpec())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeuQ").
                body(customerName).
                patch("/orders/{orderId}").
                then().statusCode(204).
                log().all();
    }

    @Test
    void TC2_update_existing_order_with_authentication_and_empty_body(){

        given()
                .spec(setUp.getRequestSpec())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeuQ").
                body("").
                patch("/orders/{orderId}").
                then().statusCode(204).
                log().all();
    }

    @Test
    void TC3_update_inexisting_order_with_authentication() throws JsonProcessingException {
        String customerName = orderInfo.updateOrderCustomerName("AnyName");

        given()
                .spec(setUp.getRequestSpec())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeu").
                body(customerName).
                patch("/orders/{orderId}").
                then().statusCode(404).
                log().all().
                body("error", equalTo("No order with id cCP47zVMdJqpWcYpFKeu."));
    }

    @Test
    void TC4_update_existing_order_without_authentication() throws JsonProcessingException {
        String customerName = orderInfo.updateOrderCustomerName("AnyName");
        given()
                .spec(setUp.getRequestSpecWithoutAuth())
                .pathParam("orderId","cCP47zVMdJqpWcYpFKeuQ").
                body(customerName).
                patch("/orders/{orderId}").
                then().statusCode(401).
                log().all().
                body("error", equalTo("Missing Authorization header."));
    }

}
