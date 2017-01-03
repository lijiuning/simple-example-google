package com.suvorov;

import com.suvorov.categories.HighPriority;
import com.suvorov.categories.MediumPriority;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by vsuvorov on 12/29/16.
 * API Documentation:
 * https://developers.google.com/maps/documentation/distance-matrix/intro#DistanceMatrixRequests
 */
public class gMapsDistanceMatrixTests {

    String apiType, units, origins, destinations;

    @Before
    public void setupEnv() {
        RestAssured.baseURI = "https://maps.googleapis.com/maps/api";
        RestAssured.basePath = "/distancematrix";

//        origins = "Washington,DC|Boston,MA";
//        destinations = "Dallas,TX|San+Francisco,CA";
//https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC|Boston,MA&destinations=Dallas,TX|San+Francisco,CA
    }

    @Category({HighPriority.class})
    @Test
    public void basicResponse() {
        setParameters("json", "imperial", "Washington,DC", "Dallas,TX");
        given().
                params(getParametersMap()).
                when().
                get("/{apiType}", apiType).
                then().
                statusCode(200).
                body("status", equalTo("OK"));
    }

    @Category({HighPriority.class})
    @Test
    public void checkStructure() {
        setParameters("json", "imperial", "Washington,DC", "Dallas,TX");
        given().
                params(getParametersMap()).
                when().
                get("/{apiType}", apiType).
                then().
                body("origin_addresses", hasSize(1)).
                body("destination_addresses", hasSize(1)).
                body("rows", hasSize(1)).
                body("rows.elements.distance.text", hasSize(1)).
                body("rows.elements.distance.value", hasSize(1)).
                body("rows.elements.duration.text", hasSize(1)).
                body("rows.elements.duration.value", hasSize(1)).
                body("status", equalTo("OK")).
                body("rows.elements.status", equalTo(Arrays.asList(Arrays.asList("OK"))));
    }

    @Category({MediumPriority.class})
    @Test
    public void invalidRequest() {
        setParameters("json", "imperial", "", "Dallas,TX");
        given().
                params(getParametersMap()).
                when().
                get("/{apiType}", apiType).
                then().
                statusCode(200).
                body("status", equalTo("INVALID_REQUEST"));
    }

    private void setParameters(String apiType, String units, String origins, String destinations) {
        this.apiType = apiType;
        this.units = units;
        this.origins = origins;
        this.destinations = destinations;
    }

    private Map<String, String> getParametersMap() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("units", units);
        params.put("origins", origins);
        params.put("destinations",destinations);
        return params;
    }

    @After
    public void cleanEnv() {

    }
}
