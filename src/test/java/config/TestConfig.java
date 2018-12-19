package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class TestConfig {

    public static RequestSpecification videoGame_requestSpec;
    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() {

        RestAssured.proxy("localhost", 8888);

        videoGame_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://localhost").
                setPort(8080).
                setBasePath("/app/").
                addHeader("Content-type", "application/json").
                addHeader("Accept", "application/json").
                build();

        football_requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.football-data.org").
                setBasePath("/v2/").
                addHeader("X-Auth-Token", "b43da6ac10004833a55dfe7fd7ae3875").
                addHeader("X-Response-Control", "minified").
                build();

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                build();

        RestAssured.responseSpecification = responseSpec;
    }
}
