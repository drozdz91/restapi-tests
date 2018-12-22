import config.EndPoint;
import config.TestConfig;
import org.testng.annotations.Test;

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
}
