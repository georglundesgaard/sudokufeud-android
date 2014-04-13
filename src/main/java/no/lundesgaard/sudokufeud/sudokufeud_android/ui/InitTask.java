package no.lundesgaard.sudokufeud.sudokufeud_android.ui;

import android.os.AsyncTask;
import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.State;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.GameFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.GamesServiceClient;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.ProfileServiceClient;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.*;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.BusProvider;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import org.androidannotations.annotations.EBean;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.Arrays;

@EBean
public class InitTask extends AsyncTask <State, String, Boolean> {

	ProfileServiceClient profileServiceClient = new ProfileServiceClient();
	GamesServiceClient gamesServiceClient = new GamesServiceClient();

	private static final String USER1 = "geiringe9";
	private static final String USER2 = "anders9";

	@Override
	protected Boolean doInBackground(State... States) {
		Log.d(Constants.TAG,"Init task starter");
		State state = States[0];

		try {
			if (state.mainPlayerAuth == null) {
				Log.d(Constants.TAG,"registerer " + USER1);
				state.mainPlayerAuth = registerUser(USER1,"GeirInge","geiringe");
			}

			publishProgress("mainPlayerAuth = " + state.mainPlayerAuth);

			if (state.otherPlayerAuth == null) {
				Log.d(Constants.TAG,"registerer " + USER1);
				state.otherPlayerAuth = registerUser(USER2,"Anders","anders");
			}

			publishProgress("otherPlayerAuth = " + state.otherPlayerAuth);

			if (state.gameId == null) {
				String gameId = gamesServiceClient.createGame(state.mainPlayerAuth, USER2, Difficulty.EASY);
				publishProgress("gameId = " + gameId);

				boolean ok = gamesServiceClient.respondToGameInvite(state.otherPlayerAuth, gameId,
						new GameResponse(ResponseValues.ACCEPT.name()));
				if (!ok)
					throw new Exception("Failed to accept game");

				publishProgress("gameId " + gameId + " accepted");

				state.gameId = gameId;
				state.isOriginalPlayer = true;
			}

			gamesServiceClient.getGame(state.mainPlayerAuth, state.gameId);

		} catch (RetrofitError e) {
			final Response response = e.getResponse();
			Log.e(Constants.TAG, "Failed to init game: " + response.getStatus() + ":" + response.getReason(),e);
		} catch (Exception e) {
			Log.e(Constants.TAG, "Failed to init game", e);
		}

		return true;
	}

	private String registerUser(String id, String name, String password) {
		return profileServiceClient.createProfile(new NewProfile(id,name,password));
	}

	@Override
	protected void onProgressUpdate(String... values) {
		Log.d(Constants.TAG,"Inittask: " + Arrays.toString(values));
	}

	@Override
	protected void onPostExecute(Boolean aBoolean) {
		Log.d(Constants.TAG,"Inittask ferdig: " + aBoolean);

	}

}
