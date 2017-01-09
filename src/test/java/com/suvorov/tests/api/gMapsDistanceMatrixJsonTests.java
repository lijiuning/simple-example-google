package com.suvorov.tests.api;

import com.jayway.jsonpath.*;
import com.suvorov.categories.*;
import com.suvorov.helpers.RestObject;
import io.restassured.RestAssured;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by vsuvorov on 12/29/16.
 * Documentation of API under test: https://developers.google.com/maps/documentation/distance-matrix/intro#DistanceMatrixRequests
 * Request example: https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC|Boston,MA&destinations=Dallas,TX|San+Francisco,CA
 *
 * For testing purposes we consider that we don't have JSON Schema to validate a structure of our responses.
 *
 * For demonstration purposes some pieces of code represents RESTAssured approach only (without using Response object or JsonPath library).
 * And vice versa some tests represents approach with using JsonPath library
 */

public class gMapsDistanceMatrixJsonTests {

    @Rule public TestName name = new TestName();
    private final Logger logger = LoggerFactory.getLogger(gMapsDistanceMatrixJsonTests.class);
    private HashMap<String, String> parametersMap;

    @Before
    public void setupEnv() {
        RestAssured.baseURI = "https://maps.googleapis.com/maps/api";
        RestAssured.basePath = "/distancematrix/json";
    }

    @Category({HighPriority.class})
    @Test()
    public void checkStatusSuccess() {
        logger.info("Test started: " + name.getMethodName());

        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "Washington,DC",
                "destinations", "Dallas,TX");

        given().params(parametersMap).
                when().get().
                then().statusCode(200).body("status", equalTo("OK"));
    }

    /**
     * This piece of code represents RESTAssured approach without using Response object or JsonPath library
     */
    @Category({HighPriority.class})
    @Test
    public void checkStructureBasic() {
        logger.info("Test started: " + name.getMethodName());
        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "Washington, DC",
                "destinations", "Dallas, TX");

        given().params(parametersMap).
                when().get().
                then().
                body("origin_addresses", hasSize(1)).
                body("destination_addresses", hasSize(1)).
                body("rows", hasSize(1)).
                body("rows.elements", hasSize(1)).
                body("rows.elements.distance", hasSize(1)).
                body("rows.elements.distance.text", hasSize(1)).
                body("rows.elements.distance.value", hasSize(1)).
                body("rows.elements.duration", hasSize(1)).
                body("rows.elements.duration.text", hasSize(1)).
                body("rows.elements.duration.value", hasSize(1)).
                body("status", equalTo("OK")).
                body("rows.elements.status", equalTo(Arrays.asList(Arrays.asList("OK"))));
    }

    /**
     * This piece of code represents approach with using JsonPath library
     */
    @Category({MediumPriority.class})
    @Test
    public void checkStructureForTwoDestinations() {
        logger.info("Test started: " + name.getMethodName());

        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "New York, NY",
                "destinations", "Washington, DC|Dallas, TX");

        //Getting ReadContect of Json response
        ReadContext context = RestObject.getJsonContext(parametersMap);

        //Asserting that there is only one value of origins addresses parameter
        Assert.assertThat(((ArrayList<String>) context.read("$.origin_addresses")).size(), equalTo(1));

        //Asserting that there are two values of destinations addresses parameter
        Assert.assertThat(((ArrayList<String>) context.read("$.destination_addresses")).size(), equalTo(2));

        //Asserting that there is only one row (one object in 'rows' array) - because of one place of origin
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[*]")).size(), equalTo(1));

        //Asserting that there are two elements (because of 2 destinations) in that single row
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[0].elements")).size(), equalTo(2));
    }

    @Category({MediumPriority.class})
    @Test
    public void checkStructureOfElement() {
        logger.info("Test started: " + name.getMethodName());

        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "New York, NY",
                "destinations", "Washington, DC|Dallas, TX");

        //Getting ReadContect of Json response
        ReadContext context = RestObject.getJsonContext(parametersMap);

        //Asserting that there are three sub-elements (distance, duration, status) for each element
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[0].elements[0].[*]")).size(), equalTo(3));
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[0].elements[1].[*]")).size(), equalTo(3));

        //Asserting that 'distance' and 'duration' objects are maps containing 2 key-value pairs each
        Assert.assertThat(((HashMap<String, String>) context.read("$.rows[0].elements[0].distance")).size(), equalTo(2));
        Assert.assertThat(((HashMap<String, String>) context.read("$.rows[0].elements[0].duration")).size(), equalTo(2));
    }

    @Category({MediumPriority.class})
    @Test
    public void checkTypesOfReturnedValues() {
        logger.info("Test started: " + name.getMethodName());

        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "New York, NY",
                "destinations", "Dallas, TX");

        //Getting ReadContect of Json response
        ReadContext context = RestObject.getJsonContext(parametersMap);

        //Asserting types of actual values of elements
        Assert.assertThat(context.read("$.rows[0].elements[0].distance.text"), isA(String.class));
        Assert.assertThat(context.read("$.rows[0].elements[0].distance.value"), isA(Integer.class));
        Assert.assertThat(context.read("$.rows[0].elements[0].duration.text"), isA(String.class));
        Assert.assertThat(context.read("$.rows[0].elements[0].duration.value"), isA(Integer.class));
        Assert.assertThat(context.read("$.rows[0].elements[0].status"), not(isA(Integer.class)));
    }

    @Category({MediumPriority.class})
    @Test
    public void checkStructureForTwoOrigins() {
        logger.info("Test started: " + name.getMethodName());

        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "New York, NY|Chicago, IL",
                "destinations", "Dallas, TX");

        //Getting ReadContext of Json response
        ReadContext context = RestObject.getJsonContext(parametersMap);

        //Asserting that there are two values of origins addresses parameter
        Assert.assertThat(((ArrayList<String>) context.read("$.origin_addresses")).size(), equalTo(2));

        //Asserting that there are only one value of destinations addresses parameter
        Assert.assertThat(((ArrayList<String>) context.read("$.destination_addresses")).size(), equalTo(1));

        //Asserting that there are two rows (2 objects in 'rows' array) - because of two places of origin
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[*]")).size(), equalTo(2));

        //Asserting that there is only one element (because of 1 destination) in each row
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[0].elements")).size(), equalTo(1));
        Assert.assertThat(((ArrayList<String>) context.read("$.rows[1].elements")).size(), equalTo(1));
    }

    //TODO
    @Category({HighPriority.class})
    @Ignore("Not ready yet")
    public void checkStructureForTwoOriginsAndTwoDestinations() {
        logger.info("Test started: " + name.getMethodName());

    }

    //TODO
    @Category({HighPriority.class})
    @Ignore("Not ready yet")
    public void checkDistanceImperialUnits() {
        logger.info("Test started: " + name.getMethodName());

    }

    //TODO
    @Category({HighPriority.class})
    @Ignore("Not ready yet")
    public void checkDistanceMetricUnits() {
        logger.info("Test started: " + name.getMethodName());

    }

    //TODO
    @Category({HighPriority.class})
    @Ignore("Not ready yet")
    public void checkDistanceValueAndText() {
        logger.info("Test started: " + name.getMethodName());

    }

    @Category({MediumPriority.class})
    @Test
    public void checkStatusAfterInvalidRequest() {
        logger.info("Test started: " + name.getMethodName());
        parametersMap = RestObject.setParametersMap(
                "units", "imperial",
                "origins", "New York, NY",
                "destinations", "");
        given().params(parametersMap).
                when().get().
                then().assertThat().statusCode(200).
                and().body("status", equalTo("INVALID_REQUEST"));
    }

    @After
    public void cleanEnv() {

    }
}
