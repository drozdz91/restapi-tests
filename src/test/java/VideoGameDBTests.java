import config.EndPoint;
import config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDBTests extends TestConfig {

    @Test
    public void getAllGames() {
        given().
                spec(videoGame_requestSpec).
                log().
                all().
         when().get(EndPoint.VIDEOGAMES).
         then().
                spec(responseSpec).
                log().
                all();
    }
}
