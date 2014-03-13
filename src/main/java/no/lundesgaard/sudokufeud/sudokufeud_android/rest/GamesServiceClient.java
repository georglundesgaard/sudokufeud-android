package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class GamesServiceClient {

    public Game getGame(/*String authenticationHeader, String gameId*/) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sudokufeud.lundesgaard.no/api")
                .build();

        // Create an instance of our GamesService API interface.
        GamesService gamesService = restAdapter.create(GamesService.class);

        try {
            Game game = gamesService.getGame("Basic YW5kZXJzOmFuZGVyczEyMw==", "e21b220f-76c5-408a-a667-290f78a9133f");
            System.out.println(game.getBoard());
            return game;
        }
        catch (RetrofitError e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String... args) {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sudokufeud.lundesgaard.no/api")
                .build();

        // Create an instance of our GamesService API interface.
        GamesService gamesService = restAdapter.create(GamesService.class);

        try {
            Game game = gamesService.getGame("Basic YW5kZXJzOmFuZGVyczEyMw==", "e21b220f-76c5-408a-a667-290f78a9133f");
            System.out.println(game.getBoard());
        }
        catch (RetrofitError e) {
            e.printStackTrace();
        }
    }
}
