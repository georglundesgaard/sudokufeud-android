package no.lundesgaard.sudokufeud.sudokufeud_android.tasks;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.State;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.GameFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.GamesServiceClient;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;

import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean
public class CallGamesServiceTask extends BaseTask {

	@Bean
	State state;

    @Background
    public void initializeGame() {

		if (state == null || !state.isFilled()) {
//			Log.e(Constants.TAG, "initializeGame: mangler state - henter spill");
//			GamesServiceClient gamesServiceClient = new GamesServiceClient();

//			Game game = gamesServiceClient.getGame();

		} else {
			Log.e(Constants.TAG, "initializeGame: har state - henter IKKE spill");
		}
    }
}
