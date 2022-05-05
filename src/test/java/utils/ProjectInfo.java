package utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo implements RequestCapability {
    private String baseUri;
    private String projectKey;
    private List<Map<String, String>> issueTypes;
    private Map<String, List<Map<String, String>>> resProjectInfo;

    public ProjectInfo(String baseUri, String projectKey) {
        this.baseUri = baseUri;
        this.projectKey = projectKey;
        getProjectInfo();
    }

    public String getIssueTypeId(String issueTypeStr) {
        getIssueTypes();

        String issueTypeId = null;

        for (Map<String, String> issueType : issueTypes) {
            if (issueType.get("name").equalsIgnoreCase(issueTypeStr)) {
                issueTypeId = issueType.get("id");
                break;
            }
        }

        if (issueTypeId == null) {
            throw new RuntimeException("ERROR: Could not find the id for" + issueTypeStr);
        }
        return issueTypeId;
    }

    private void getIssueTypes(){
        issueTypes = resProjectInfo.get("issueTypes");
    }

    private void getProjectInfo() {
        String path = "/rest/api/3/project/".concat(projectKey);

        //Get data from env file
        Dotenv dotenv = Dotenv.load();
        String email = dotenv.get("EMAIL_VAR");
        String apiToken = dotenv.get("API_TOKEN");

        //setup auth by encode base64
        String cred = email.concat(":").concat(apiToken);
        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
        String encodedCredStr = new String(encodedCred);// change byte format into string

        //Form up request instance, header and baseUri
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(getAuthenticatedHeader.apply(encodedCredStr));

        Response response = request.get(baseUri + path);

        resProjectInfo = JsonPath.from(response.asString()).get();
    }
}
