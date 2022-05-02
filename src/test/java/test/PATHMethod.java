package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildModelJSON;
import model.POSTBody;
import model.RequestCapability;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PATHMethod {
    public static void main(String[] args) {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        //Form up request instance, header and baseUri
        RequestSpecification request = given();
        request.baseUri(baseUrl);
        request.header(RequestCapability.defaultHeader);

        // Form up body
        POSTBody postBody = new POSTBody();
        postBody.setTitle("New Title");
        String postBodyStr = BuildModelJSON.parseJSONString(postBody);

        // Send request
        final String TARGET_POST_ID ="1";
        Response response = request.body(postBodyStr).patch("/posts/".concat(TARGET_POST_ID));
        response.then().body("title",equalTo(postBody.getTitle()));
        response.prettyPrint();
    }
}
