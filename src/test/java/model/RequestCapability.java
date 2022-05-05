package model;

import io.restassured.http.Header;

import java.util.function.Function;

public interface RequestCapability {

    Header defaultHeader = new Header("Content-type", "application/json; charset=UTF-8");

    //Method 1
    static Header getAuthenticatedHeader(String encodedCredStr) {
        if (encodedCredStr == null) {
            throw new IllegalArgumentException("ERROR: ENCODED CREDENTIAL IS NOT NULL");
        } else if (encodedCredStr.isEmpty()) {
            throw new IllegalArgumentException("ERROR: ENCODED CREDENTIAL IS NOT EMPTY");
        }
        return new Header("Authorization", "Basic " + encodedCredStr);
    }

    //Method 2
    Function<String, Header> getAuthenticatedHeader = encodedCredStr -> {
        if (encodedCredStr == null) {
            throw new IllegalArgumentException("ERROR: ENCODED CREDENTIAL IS NOT NULL");
        } else if (encodedCredStr.isEmpty()) {
            throw new IllegalArgumentException("ERROR: ENCODED CREDENTIAL IS NOT EMPTY");
        }
        return new Header("Authorization", "Basic " + encodedCredStr);
    };

}
