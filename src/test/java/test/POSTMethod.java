package test;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.POSTBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;

public class POSTMethod {
    public static void main(String[] args) {
        //RequestSpecification object
        String baseUrl ="https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseUrl);
        //Content-type -> Header
        request.header((new Header("Content-type","application/json; charset=UTF-8")));

        // Form up request body
        // Using normal
//        String postBody="{\n" +
//                "  \"userId\": 1,\n" +
//                "  \"id\": 1,\n" +
//                "  \"title\": \"REQUEST title\",\n" +
//                "  \"body\": \"The request's body\"\n" +
//                "}";

        //Using GSON
        Gson gson = new Gson();
        POSTBody postBody = new POSTBody();
        postBody.setUserId(1);
        postBody.setTitle("REQUEST title GSON");
        postBody.setBody("The request's body GSON");


        //Send POST request
        Response response = request.body(gson.toJson(postBody)).post("/posts");
        response.prettyPrint();
        System.out.println("Status code: "+ response.statusCode());
        System.out.println("Status line: "+ response.statusLine());

        //Verification
        response.then().statusCode(equalTo(201));
        response.then().statusLine(containsStringIgnoringCase("201 Created"));
        response.then().body("userId",equalTo(1));
        response.then().body("title",equalTo("REQUEST title GSON"));
        response.then().body("body",equalTo("The request's body GSON"));
    }
}
