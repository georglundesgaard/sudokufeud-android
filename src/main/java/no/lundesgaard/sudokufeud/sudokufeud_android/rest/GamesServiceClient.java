package no.lundesgaard.sudokufeud.sudokufeud_android.rest;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.FetcheGameEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.GameFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.*;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.BusProvider;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.ResponseUtil;
import retrofit.Callback;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

import java.util.List;

public class GamesServiceClient {

	public void playRound (String authentication, String gameId, Round round) {
//		Log.d(Constants.TAG,"playRound (" + authentication + "," + gameId + "," + round + ") called");
		GamesService gamesService = createGamesService();

		Callback<Void> callback = new Callback<Void>() {
			@Override
			public void success(Void o, Response response) {
				Log.e(Constants.TAG,"callback success: " + ResponseUtil.formatResponse(response)  + " kallt");
				BusProvider.updateUi(new FetcheGameEvent());
			}

			@Override
			public void failure(RetrofitError e) {
				Log.e(Constants.TAG,"callback failure: " + ResponseUtil.formatResponse(e.getResponse()));
// Todo: Varsle brukeren
			}
		};

		try {
			gamesService.playRound(authentication, gameId, round, callback);
			Log.e(Constants.TAG, "playRound ferdig");
		} catch (RetrofitError e) {
			Log.e(Constants.TAG, "playRound Feilet: " + ResponseUtil.formatResponse(e.getResponse()),e);
		}
	}

	public boolean respondToGameInvite(String authentication, String gameId, GameResponse gameResponse) {
		GamesService gamesService = createGamesService();

		try {
			gamesService.respondToGameInvite(authentication, gameId, gameResponse);
			return true;
		} catch (RetrofitError e) {
			Log.e(Constants.TAG, "respondToGameInvite Feilet: " + ResponseUtil.formatResponse(e.getResponse()),e);
		}
		return false;
	}

	public String createGame(String authentication, String opponent, Difficulty level) {
		GamesService gamesService = createGamesService();

		Response response;
		try {
			response = gamesService.createGame(authentication, new JsonNewGame(opponent, level.name()));
		} catch (RetrofitError e) {
			Log.e(Constants.TAG, "createGame Feilet: " + ResponseUtil.formatResponse(e.getResponse()),e);
			return null;
		}

		final List<Header> headers = response.getHeaders();
		for (Header header : headers) {
			if ("Location".equals(header.getName())) {
				String location = header.getValue();
				int i = location.lastIndexOf("/");
				if (i >= 0)
					return location.substring(i+1);
			}
		}
		return null;
	}

	public void getGame(String authenticationHeader, String gameId) {
		GamesService gamesService = createGamesService();

		Callback<Game> callback = new Callback<Game>() {
			@Override
			public void success(Game game, Response response) {
				Log.e(Constants.TAG,"callback success: " + ResponseUtil.formatResponse(response)  + " kallt");
				BusProvider.updateUi(new GameFetchedEvent(game));
			}

			@Override
			public void failure(RetrofitError e) {
				Log.e(Constants.TAG,"callback failure: " + ResponseUtil.formatResponse(e.getResponse()));
// Todo: Varsle brukeren
			}
		};

		try {
			gamesService.getGame(authenticationHeader, gameId, callback);
		} catch (RetrofitError e) {
			Log.e(Constants.TAG, "getGame Feilet: " + ResponseUtil.formatResponse(e.getResponse()),e);
		}
    }

	private GamesService createGamesService() {
		final RestAdapter.Builder builder = new RestAdapter.Builder();
		builder
				.setEndpoint("http://sudokufeud.lundesgaard.no/api")
//				.setEndpoint("http://192.168.1.24:8080/api")
				.build();

/*				builder.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade requestFacade) {
						requestFacade.addHeader("Authorization", authorization);
					}
				});
*/

		// Create an instance of our GamesService API interface.
		return builder.build().create(GamesService.class);
	}
}
