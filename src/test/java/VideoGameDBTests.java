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
        when().
                get(EndPoint.VIDEOGAMES).
        then().
                spec(responseSpec).
                log().
                all();
    }

    @Test
    public void createNewGameByJSON() {

        String gameBodyJson = "{\n" +
                "  \"id\": 15,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2018-12-19T20:45:20.180Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().
                spec(videoGame_requestSpec).
                body(gameBodyJson).
        when().
                post(EndPoint.VIDEOGAMES).
        then();
    }
}
