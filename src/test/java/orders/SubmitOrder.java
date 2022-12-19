package orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.specification.RequestSpecification;
import model.OrderInfo;
import org.junit.Before;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import setUp.SetUp;


public class SubmitOrder {

    SetUp setUp = new SetUp();
    OrderInfo orderInfo = new OrderInfo();

    private static RequestSpecification spec;

    @Before
    void setUp() {
        System.out.println("Set Up");
    }

    @Test
    void TC1_submit_an_order() throws JsonProcessingException {
        String addBookDetails = orderInfo.setOrderInfo("John", 4);

        given().spec(SetUp.getRequestSpecWithAuth())
                .body(addBookDetails).
                post("/orders").
                then().
                statusCode(201).
                log().all();
    }

    @Test
    void TC2_submit_an_order_when_customerName_null()throws JsonProcessingException {
        String addBookDetails = orderInfo.setOrderInfo("", 6);

        given().
                spec(SetUp.getRequestSpecWithAuth())
                .body(addBookDetails).
                post("/orders").
                then().
                statusCode(400).
                log().all();
    }

    @Test
    void TC3_submit_an_order_when_bookId_invalid_and_customerName_null()throws JsonProcessingException {
        String addBookDetails = orderInfo.setOrderInfo("", 7);

        given().spec(SetUp.getRequestSpecWithAuth())
                .body(addBookDetails).
                post("/orders").
                then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid or missing bookId."));
    }

    @Test
    void TC4_submit_an_order_when_book_not_available()throws JsonProcessingException {
        String addBookDetails = orderInfo.setOrderInfo("John", 2);

        given().spec(SetUp.getRequestSpecWithAuth())
                .body(addBookDetails).
                post("/orders").
                then().
                statusCode(404).
                log().all().
                body("error", equalTo("This book is not in stock. Try again later."));
    }


    @Test
    void TC5_submit_an_order_without_auth()throws JsonProcessingException {
        String addBookDetails = orderInfo.setOrderInfo("John", 4);

        given().spec(SetUp.getRequestSpecWithoutAuth())
                .body(addBookDetails).
                post("/orders").
                then().
                statusCode(401).
                log().all().
                body("error", equalTo("Missing Authorization header."));
    }


}
