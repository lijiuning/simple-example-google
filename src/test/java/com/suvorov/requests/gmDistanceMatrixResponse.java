package com.suvorov.requests;

import com.suvorov.helpers.RestObject;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * Created by vsuvorov on 1/7/17.
 */
public class gmDistanceMatrixResponse extends RestObject {

//    String apiType, units, origins, destinations;
    final Logger LOGGER = LoggerFactory.getLogger(gmDistanceMatrixResponse.class);

    public gmDistanceMatrixResponse(String api, String units, String origins, String destinations) {
        Response response =
                given().params(setParametersMap("units", units, "origins", origins, "destinations", destinations)).
                when().get("/{api}", api).
                then().statusCode(200).
                extract().response();
        return;
    }
}
