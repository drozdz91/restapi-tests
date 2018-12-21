import config.EndPoint;
import config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

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
}
