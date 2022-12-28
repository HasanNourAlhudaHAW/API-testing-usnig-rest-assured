package orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.ClientInfo;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;

import setUp.SetUp;

public class CreateAccessToken {

    SetUp setUp = new SetUp();
    ClientInfo clientInfo = new ClientInfo();


    @BeforeSuite
    void setUp(){
        System.out.println("SetUp");
    }



    @Test
    void TC1_create_new_accessToken() throws JsonProcessingException {
        String addClientInfo = clientInfo.setClientInfo("AnyName", "anyName@edu.de");

         given().
                 spec(SetUp.getRequestSpecWithAuth()).
                 body(addClientInfo).
                 post("/api-clients").
                then().
                statusCode(201).
                log().all();
    }

    @Test
    void TC2_create_new_accessToken_with_existing_clientEmail() throws JsonProcessingException {
        String addClientInfo = clientInfo.setClientInfo("AnyName", "anyName@edu.de");

        given().
                spec(SetUp.getRequestSpecWithAuth()).
                body(addClientInfo).
                post("/api-clients").
                then().
                statusCode(409).
                log().all().
                body("error", equalTo("API client already registered. Try a different email."));
    }

    @Test
    void TC3_create_new_accessToken_with_invalid_Email_format() throws JsonProcessingException {
        String addClientInfo = clientInfo.setClientInfo("AnyName", "anyName1");

        given().
                spec(SetUp.getRequestSpecWithAuth()).
                body(addClientInfo).
                post("/api-clients").
                then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid or missing client email."));
    }

    // TC4: Create new accessToken with missing clientName & inexisting clientEmail(email format)
    @Test
    void TC4_create_new_accessToken_without_clientName() throws JsonProcessingException {
        String addClientInfo = clientInfo.setClientInfo("","AnyName4@gmail.com");

        given().
                spec(SetUp.getRequestSpecWithAuth()).
                body(addClientInfo).
                post("/api-clients").
                then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid or missing client name."));
    }

    @Test
    void TC5_create_new_accessToken_without_clientEmail() throws JsonProcessingException {
        String addClientInfo = clientInfo.setClientInfo("John","");

        given().
                spec(SetUp.getRequestSpecWithAuth()).
                body(addClientInfo).
                post("/api-clients").
                then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid or missing client email."));
    }

    @Test
    void TC6_create_new_accessToken_with_null_clientName_and_inexisting_clientEmail() throws JsonProcessingException {
        String addClientInfo = clientInfo.setClientInfo("", "anyName22@edu.de");

        given().
                spec(SetUp.getRequestSpecWithAuth()).
                body(addClientInfo).
                post("/api-clients").
                then().
                statusCode(400).
                log().all().
                body("error", equalTo("Invalid or missing client name."));
    }


    @AfterSuite
    void tearDown(){
        System.out.println("TearDown");
    }
}