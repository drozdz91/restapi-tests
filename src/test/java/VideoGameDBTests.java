import config.EndPoint;
import config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
                log().
                all();
    }

    @Test
    public void getSingleGame() {
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 10).
        when().
                get(EndPoint.SINGLE_VIDEOGAME).
        then();
    }

    @Test
    public void createNewGameByJSON() {

        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
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

    @Test
    public void createNewGameByXML() {

        String gameBodyXml = "<videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>12</id>\n" +
                "    <name>Resident Evil 7</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+02:00</releaseDate>\n" +
                "    <reviewScore>75</reviewScore>\n" +
                "  </videoGame>";

        given().
                spec(videoGame_requestSpec).
                body(gameBodyXml).
        when().
                post(EndPoint.VIDEOGAMES).
        then();
    }

    @Test
    public void updateGame() {

        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"MyUpdatedGame\",\n" +
                "  \"releaseDate\": \"2018-12-19T20:45:20.180Z\",\n" +
                "  \"reviewScore\": 99,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 11).
                body(gameBodyJson).
        when().
                put(EndPoint.SINGLE_VIDEOGAME).
        then();
    }

    @Test
    public void deleteGame() {
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 11).
        when().
                delete(EndPoint.SINGLE_VIDEOGAME).
        then();
    }

    @Test
    public void testVideoGameSerialisationByJSON() {

        VideoGame videoGame = new VideoGame("15", "shooter", "2018-12-12", "Fifa 20",
                "Kids", "89");

        given().
                spec(videoGame_requestSpec).
                body(videoGame).
        when().
                post(EndPoint.VIDEOGAMES).
        then();
    }

    @Test
    public void testVideoGameSchemaXML() {
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 5).
        when().
                get(EndPoint.SINGLE_VIDEOGAME).
        then().
                body(matchesXsdInClasspath("VideoGame.xsd"));
    }

    @Test
    public void testVideoGameSchemaJSON() {
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 5).
        when().
                get(EndPoint.SINGLE_VIDEOGAME).
        then().
                body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }
}
