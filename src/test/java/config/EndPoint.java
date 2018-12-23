package config;

public interface EndPoint {

    // Endpoints for VideoGameDB
    String VIDEOGAMES = "/videogames";
    String SINGLE_VIDEOGAME = "/videogames/{videoGameId}";

    // Endpoints for Football API
    String COMPETITIONS_PL_MATCHES = "competitions/PL/matches";
    String TEAMS_FOR_ONE_COMPETITION = "competitions/{competitionId}/teams";
}
