package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import retrofit.RequestInterceptor;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class GamesServiceClient {

    public Game getGame(/*String authenticationHeader, String gameId*/) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sudokufeud.lundesgaard.no/api")
                /*.setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade requestFacade) {
                        requestFacade.addHeader("Authorization", "Basic Z2VpcjpnZWlyMTIz");
                    }
                })*/
                .build();

        // Create an instance of our GamesService API interface.
        GamesService gamesService = restAdapter.create(GamesService.class);

        try {
            Game game = gamesService.getGame("Basic Z2VpcjpnZWlyMTIz", "405f9ec3-e379-49d4-89d6-3ec4f1cee593");
			Log.i(Constants.TAG, "getGame: fikk board " + game.getBoard());
			Log.i(Constants.TAG, "getGame: fikk AvailablePieces " + game.getAvailablePieces());
            return game;
        }
        catch (RetrofitError e) {
			Log.e(Constants.TAG, "getGame Feilet: " + e.getResponse().getStatus() + ":" + e.getResponse().getReason(),e);
        }
        return null;
    }
}
