package books;


import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import setUp.SetUp;


public class GetBooksRest1 {


    SetUp setUp = new SetUp();


    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }


    @Test
    void TC1_get_all_available_books(){
        given().spec(setUp.getRequestSpec()).
                get("/books").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    void TC2_get_books_when_limit_equalTo_1(){
        given().spec(setUp.getRequestSpec())
                .queryParam("limit","1").
                get("/books").
                then().
                statusCode(200).
                log().all().
                body("[0].name", equalTo("The Russian"));
    }
    @Test
    void TC3_get_books_when_limit_equalTo_20(){
        given().spec(setUp.getRequestSpec())
                .queryParam("limit","20").
                get("/books").
                then().
                statusCode(200).
                log().all().
                body("[5].name", equalTo("Viscount Who Loved Me"));
    }


    @Test
    void TC4_get_books_when_type_equalTo_fiction(){
        given().spec(setUp.getRequestSpec())
                .queryParam("type","fiction").
                get("/books").
                then().
                statusCode(200).
                log().all().
                body("[2].type", equalTo("fiction"));
    }

    @Test
    void TC5_get_books_when_type_equalTo_non_fiction(){
        given().spec(setUp.getRequestSpec())
                .queryParam("type","non-fiction").
                get("/books").
                then().
                statusCode(200).
                log().all();
    }


    @Test
    void TC6_get_books_when_limit_equalTo_1_and_type_equalTo_fiction(){
        given().spec(setUp.getRequestSpec())
                .queryParam("limit",1).queryParam("type","fiction").
                get("/books").
                then().
                statusCode(200).
                log().all();
    }

    /**
     * Expected status code <400> Bad Request but was <200> Ok.
     */
    @Test
    void TC7_get_books_when_limit_equalTo_0(){

        given().spec(setUp.getRequestSpec()).
                queryParam("limit","0").get("/books")
                .then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid value for query parameter 'limit'. Must be greater than 0."));
    }

    @Test
    void TC8_get_books_when_limit_equalTo_21(){

        given().spec(setUp.getRequestSpec()).
                queryParam("limit","21").get("/books")
                .then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid value for query parameter 'limit'. Cannot be greater than 20."));
    }



    @Test
    void TC9_get_books_when_type_equalTo_comedy(){

        given().spec(setUp.getRequestSpec()).
                queryParam("type","comedy").get("/books")
                .then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid value for query parameter 'type'. Must be one of: fiction, non-fiction."));
    }

    // Expected status code <400> Bad Request but was <200> Ok.
    @Test
    void TC10_get_books_when_type_equalTo_null(){

        given().spec(setUp.getRequestSpec()).
                queryParam("type","").get("/books")
                .then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid value for query parameter 'type'. Must be one of: fiction, non-fiction."));
    }


    @AfterSuite
    void tearDown(){
        System.out.println("TearDown");
    }
}
