import config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends TestConfig {

    @Test
    public void myFirstTest() {
        given().
                log().
                all().
        when().get("videogames/1").
        then().
                log().
                all();
    }
}
