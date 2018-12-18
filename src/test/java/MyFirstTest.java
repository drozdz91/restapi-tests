import config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends TestConfig {

    @Test
    public void myFirstTest() {
        given().
                spec(videoGame_requestSpec).
                log().
                all().
        when().get("videogames/1").
        then().
                spec(responseSpec).
                log().
                all();
    }
}
