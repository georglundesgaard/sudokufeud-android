package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface GamesService {

    @GET("/games/{gameId}")
    Game getGame(@Header("Authorization") String authorization, @Path("gameId") String gameId);

}
