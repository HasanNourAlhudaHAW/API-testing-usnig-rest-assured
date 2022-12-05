package books;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setUp.SetUp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSingleBookRest2 {

    SetUp setUp = new SetUp();

    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }


    @Test
    void TC1_get_book_when_bookId_equalTo_1(){
            given()
                .spec(setUp.getRequestSpec())
                .pathParam("bookid","1").
                get("/books/{bookid}").
                then().statusCode(200).
                log().all().
                    body("id", equalTo(1));
    }

    @Test
    void TC2_get_book_when_bookId_equalTo_6(){
        given()
                .spec(setUp.getRequestSpec())
                .pathParam("bookid","6").
                get("/books/{bookid}").
                then().statusCode(200).
                log().all().
                body("id", equalTo(6));
    }

    @Test
    void TC3_get_book_when_bookId_equalTo_0(){
        given()
                .spec(setUp.getRequestSpec())
                .pathParam("bookid","0").
                get("/books/{bookid}").
                then().statusCode(404).
                log().all().
                body("error", equalTo("No book with id 0"));
    }

    @Test
    void TC4_get_book_when_bookId_equalTo_7(){
        given()
                .spec(setUp.getRequestSpec())
                .pathParam("bookid","7").
                get("/books/{bookid}").
                then().statusCode(404).
                log().all().
                body("error", equalTo("No book with id 7"));
    }

    @Test
    void TC5_get_book_when_bookId_equalTo_null(){
        given()
                .spec(setUp.getRequestSpec())
                .pathParam("bookid","Nan").
                get("/books/{bookid}").
                then().statusCode(404).
                log().all().
                body("error", equalTo("No book with id NaN"));
    }



    @AfterSuite
    void tearDown(){
        System.out.println("TearDown");
    }
}
