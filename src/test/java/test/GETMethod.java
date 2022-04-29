package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GETMethod {

    public static void main(String[] args) {
        String baseUrl ="https://jsonplaceholder.typicode.com";

        //Request Scope
        RequestSpecification request = given();
        request.baseUri(baseUrl);
        request.basePath("/todos");

        //Response Scope
        final String FIRST_TODO ="/1";
        Response response = request.get(FIRST_TODO); //GET: https://jsonplaceholder.typicode.com/todos/1
        //print response
        response.prettyPrint();
        // check expect
        response.then().body("userId", equalTo(1));
        response.then().body("id", equalTo(1));
        response.then().body("title", equalTo("delectus aut autem"));
        response.then().body("completed", equalTo(false));

    }
}
