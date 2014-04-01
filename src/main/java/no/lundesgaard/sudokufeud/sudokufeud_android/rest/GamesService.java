package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.GameResponse;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Profile;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Round;
import retrofit.http.*;

import java.util.List;

public interface GamesService {

    @GET("/games")
    List<Game> getGames(@Header("Authorization") String authorization);

    @GET("/games/{gameId}")
    Game getGame(@Header("Authorization") String authorization, @Path("gameId") String gameId);

    @PUT("/games/{gameId}")
    void respondToGameInvite(@Header("Authorization") String authorization, GameResponse gameResponse);

    @POST("games/{gameId}/rounds")
    void playRound(@Header("Authorization") String authorization);

    @GET("games/{gameId}/rounds")
    List<Round> getPlayedRounds(@Header("Authorization") String authorization);

}
