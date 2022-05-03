package test;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;

import org.apache.commons.codec.binary.Base64;

import static io.restassured.RestAssured.given;

public class jiraIssueTypes implements RequestCapability{
    public static void main(String[] args) {
        String baseUri ="https://ducanhn.atlassian.net";
        String path="/rest/api/3/project/AT";

        //Get data from env file
        Dotenv dotenv = Dotenv.load();
        String email = dotenv.get("EMAIL_VAR");
        String apiToken =dotenv.get("API_TOKEN");

        //setup auth by encode base64
        String cred = email.concat(":").concat(apiToken);
        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
        String encodedCredStr = new String(encodedCred);// change byte format into string

        //Form up request instance, header and baseUri
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header("Authorization","Basic " +encodedCredStr);


        Response response = request.get(baseUri+path);

        response.prettyPrint();

    }
}
