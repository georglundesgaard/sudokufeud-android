package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.*;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.*;

import java.util.List;

public interface GamesService {

    @GET("/games")
    List<Game> getGames(@Header("Authorization") String authorization);

	@POST("/games")
	Response createGame(@Header("Authorization") String authorization, @Body JsonNewGame jsonNewGame);

	@GET("/games/{gameId}")
    void getGame(@Header("Authorization") String authorization, @Path("gameId") String gameId, Callback<Game> cb);

    @PUT("/games/{gameId}")
	Response respondToGameInvite(@Header("Authorization") String authorization, @Path("gameId") String gameId, @Body GameResponse gameResponse);

    @POST("/games/{gameId}/rounds")
	void playRound(@Header("Authorization") String authorization, @Path("gameId") String gameId, @Body Round round, Callback<Void> cb);

    @GET("/games/{gameId}/rounds")
    List<Round> getPlayedRounds(@Header("Authorization") String authorization);

}
