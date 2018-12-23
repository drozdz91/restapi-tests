import config.EndPoint;
import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends TestConfig {

    @Test
    public void getAllPlCompetitionsOnSpecifiedMatchday() {
        given().
                spec(football_requestSpec).
                queryParam("matchday", 11).
        when().
                get(EndPoint.COMPETITIONS_PL_MATCHES).
        then();
    }

    @Test
    public void getAllTeamsForParticularCompetition() {
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
        then().
                body("count", equalTo(18));
    }

    @Test
    public void getFirstTeamName() {
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
        then().
                body("teams.name[0]", equalTo("VVV Venlo"));
    }

    @Test
    public void getAllTeamData_DoCheckFirst() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
        then().
                contentType(ContentType.JSON).
                extract().response();

        String jsonResponseAsString = response.asString();
    }

    @Test
    public void extractHeaders() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
        then().
                contentType(ContentType.JSON).
                extract().response();

        Headers headers = response.getHeaders();
    }

    @Test
    public void extractFirstTeamName() {
        String firstTeamName =
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
                jsonPath().getString("teams.name[0]");

        System.out.println(firstTeamName);
    }

    @Test
    public void extractAllTeamNames() {
        Response response =
        given().
                spec(football_requestSpec).
                pathParam("competitionId", 2003).
        when().
                get(EndPoint.TEAMS_FOR_ONE_COMPETITION).
        then().
                contentType(ContentType.JSON).
                extract().response();

        List<String> teamNames = response.path("teams.name");

        for (String teamName: teamNames) {
            System.out.println(teamName);
        }
    }
}
