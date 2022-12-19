package setUp;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ClientInfo;

import static io.restassured.RestAssured.given;

public class SetUp {

    private String token;
    ClientInfo clientInfo=new ClientInfo();

    public static void main(String[] args) throws JsonProcessingException {
        SetUp setUp = new SetUp();
        setUp.generateNewToken();
    }

    public static RequestSpecification getRequestSpecWithAuth() {
        String token = "cacb9805661405efff18acd8df6350671884f6e7d0724ac44584b6b756db3974";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri ("https://simple-books-api.glitch.me");
        builder.addHeader("Authorization", "Bearer " + token);
        builder.addHeader("Content-Type", "application/json");

        RequestSpecification requestSpec = builder.build();
        return requestSpec;
    }

    public static RequestSpecification getRequestSpecWithoutAuth() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri ("https://simple-books-api.glitch.me");

        RequestSpecification requestSpec = builder.build();
        return requestSpec;
    }


    void generateNewToken() throws JsonProcessingException {
        String  payload =  clientInfo.setClientInfo("John", "john@gmail.com");

        RequestSpecification request = given();

        request.header("Content-Type", "application/json");
        Response responseFromTokenGenerator = request.body(payload).post("/api-clients/");
        String jsonString = responseFromTokenGenerator.getBody().asString();
        String token = JsonPath.from(jsonString).get("accessToken");
        System.out.println("The generated Token is: " + token);
    }


//    String generateNewTokenTest(String clientName, String clientEmail){
//
//        RequestSpecification request = given().spec(this.getRequestSpec());
//        String payload = "{\n" +
//                "   \"clientName\": \"clientName\",\n" +
//                "   \"clientEmail\": \"clientEmail\"\n" +
//                "}";
//        request.header("Content-Type", "application/json");
//        Response responseFromTokenGenerator = request.body(payload).post("/api-clients/");
//        String jsonString = responseFromTokenGenerator.getBody().asString();
//        final String token = JsonPath.from(jsonString).get("accessToken");
//        return  token;
//    }
//
//
//    public RequestSpecification getRequestSpecTest(String clientName, String clientEmail) {
//
//        String token =  this.generateNewTokenTest(clientName, clientEmail);
//        System.out.println("My new Token is: " + token);
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.setBaseUri ("https://simple-books-api.glitch.me");
//        builder.addHeader("Authorization", "Bearer " + token);
//        builder.addHeader("Content-Type", "application/json");
//
//        RequestSpecification requestSpec = builder.build();
//        return requestSpec;
//    }


}
