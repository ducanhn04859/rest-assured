package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.POSTBody;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PUTMethod {
    public static void main(String[] args) {
        String baseUrl ="https://jsonplaceholder.typicode.com";

        // Form up request object and header
        RequestSpecification request = given();
        request.baseUri(baseUrl);
        request.header((new Header("Content-type","application/json; charset=UTF-8")));

        // construct body

        POSTBody postBody1 = new POSTBody(1,1,"REQUEST PUT Method title","The request's body PUT Method");
        POSTBody postBody2 = new POSTBody(2,1,"REQUEST PUT Method title 2","The request's body PUT Method 2");
        POSTBody postBody3 = new POSTBody(3,1,"REQUEST PUT Method title 3","The request's body PUT Method 3");

        List<POSTBody> postBodies = Arrays.asList(postBody1,postBody2,postBody3);

        for (POSTBody postBody : postBodies) {
            Gson gson = new Gson();
            String postBodyStr = gson.toJson(postBody);
            System.out.println(postBody);
            //Send PUT request
            final int TARGET_POST_NUM =1;
            Response response = request.body(postBodyStr).put("/posts/".concat(String.valueOf(TARGET_POST_NUM)));
            response.prettyPrint();
            response.then().body("title",equalTo(postBody.getTitle()));
            response.then().body("body",equalTo(postBody.getBody()));
            response.then().body("userId",equalTo(postBody.getUserId()));
        }
    }
}
