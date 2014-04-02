package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class GamesServiceClient {

    public Game getGame(/*String authenticationHeader, String gameId*/) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sudokufeud.lundesgaard.no/api")
                .build();

        // Create an instance of our GamesService API interface.
        GamesService gamesService = restAdapter.create(GamesService.class);

        try {
            Game game = gamesService.getGame("Basic Z2VpcjpnZWlyMTIz", "39016be1-0108-407c-8a4f-aaa93c0bda69");
			Log.i(Constants.TAG, "getGame: fikk board " + game.getBoard());
			Log.i(Constants.TAG, "getGame: fikk AvailablePieces " + game.getAvailablePieces());
            return game;
        }
        catch (RetrofitError e) {
			Log.e(Constants.TAG, "getmail Feilet",e);
        }
        return null;
    }
}
