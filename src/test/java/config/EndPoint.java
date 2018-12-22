package config;

public interface EndPoint {

    String VIDEOGAMES = "/videogames";
    String SINGLE_VIDEOGAME = "/videogames/{videoGameId}";

    String COMPETITIONS_PL_MATCHES = "competitions/PL/matches";
    String TEAMS_FOR_ONE_COMPETITION = "competitions/{competitionId}/teams";
}
