package test;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;
import utils.ProjectInfo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class jiraIssueTypes_ExtractJSONPath implements RequestCapability {
    public static void main(String[] args) {
//        String baseUri = "https://ducanhn.atlassian.net";
//        String path = "/rest/api/3/project/AT";

//        //Get data from env file
//        Dotenv dotenv = Dotenv.load();
//        String email = dotenv.get("EMAIL_VAR");
//        String apiToken = dotenv.get("API_TOKEN");
//
//        //setup auth by encode base64
//        String cred = email.concat(":").concat(apiToken);
//        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
//        String encodedCredStr = new String(encodedCred);// change byte format into string
//
//        //Form up request instance, header and baseUri
//        RequestSpecification request = given();
//        request.baseUri(baseUri);
//        request.header(defaultHeader);
//
//        //Using Method 1
//        //request.header(RequestCapability.getAuthenticatedHeader(encodedCredStr));
//
//        //Using Method 2
//        request.header(getAuthenticatedHeader.apply(encodedCredStr));
//
//        Response response = request.get(baseUri + path);
//
//        //response.prettyPrint();
//
//        // Get key and value of issueTypes
//        Map<String, List<Map<String,String>>> resProjectInfo = JsonPath.from(response.asString()).get();
//        List<Map<String,String>> issueTypes = resProjectInfo.get("issueTypes");
//        for (Map<String, String> issueType : issueTypes) {
//            System.out.println(issueType.get("id"));
//            System.out.println(issueType.get("name"));
//            System.out.println("--------------------");
//
//        }

        String baseUri = "https://ducanhn.atlassian.net";
        String projectKey = "AT";

        ProjectInfo projectInfo = new ProjectInfo(baseUri,projectKey);
        System.out.println("Task ID: "+projectInfo.getIssueTypeId("task"));
    }
}
