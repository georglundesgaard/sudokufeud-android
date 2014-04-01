package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
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
            System.out.println(game.getBoard());
            System.out.println(game.getAvailablePieces());
            return game;
        }
        catch (RetrofitError e) {
            e.printStackTrace();
        }
        return null;
    }


}
