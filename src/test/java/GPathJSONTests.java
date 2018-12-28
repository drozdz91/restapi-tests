import config.EndPoint;
import config.TestConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GPathJSONTests extends TestConfig {

    @Test
    public void extractMapOfElementsWithFind() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
        then().
                extract().response();

        Map<String,?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'VVV Venlo' }");
        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("teamId", 66).
        when().
                get(EndPoint.PARTICULAR_TEAM).
        then().
                extract().response();

        String certainPlayer = response.path("squad.find { it.shirtNumber == 1 }.name");
        System.out.println(certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("teamId", 66).
        when().
                get(EndPoint.PARTICULAR_TEAM).
        then().
                extract().response();

        List<String> playerNames = response.path("squad.findAll { it.shirtNumber > 1 }.name");
        System.out.println(playerNames);
    }

    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("teamId", 66).
        when().
                get(EndPoint.PARTICULAR_TEAM).
        then().
                extract().response();

        String playerName = response.path("squad.max { it.shirtNumber }.name");
        System.out.println(playerName);
    }

    @Test
    public void extractMultipleValuesAndSumThem() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("teamId", 66).
        when().
                get(EndPoint.PARTICULAR_TEAM).
        then().
                extract().response();

        int sumOfShirtNumbers = response.path("squad.collect { it.shirtNumber }.sum()");
        System.out.println(sumOfShirtNumbers);
    }
}
