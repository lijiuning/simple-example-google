package com.suvorov.helpers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * Created by vsuvorov on 1/7/17.
 */
public class RestObject {
    public static HashMap<String, String> setParametersMap(String... keyOrValue) {
        HashMap<String, String> params = new HashMap<String, String>();
        for (int i = 0; i < keyOrValue.length; i += 2) {
            params.put(keyOrValue[i], keyOrValue[i + 1]);
        }
        return params;
    }

    public static ReadContext getJsonContext(HashMap<String, String> params) {
        String json = given().params(params).when().get().then().extract().response().asString();
        return JsonPath.parse(json);
    }
}
