package no.lundesgaard.sudokufeud.sudokufeud_android.ui;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.*;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import no.lundesgaard.sudokufeud.sudokufeud_android.State;
import no.lundesgaard.sudokufeud.sudokufeud_android.adapters.SquareAdapter;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.FetcheGameEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.GameFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Field;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.GamesServiceClient;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Move;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Round;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.BusProvider;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import no.lundesgaard.sudokufeud.sudokufeud_android.views.SquareGridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.squareup.otto.Subscribe;

@EFragment(R.layout.fragment_board)
public class BoardFragment extends Fragment {

	@ViewById
	SquareGridView square1, square2, square3, square4, square5, square6, square7, square8, square9;

	@ViewById
	RadioButton tile1, tile2, tile3, tile4, tile5, tile6, tile7;

	@ViewById
	RelativeLayout notBoard;

	@ViewById
	Button buttonSubmit;

	@ViewById
	RadioGroup radioGroupTiles;
//    RelativeRadioGroup radioGroupTiles;

	@ViewById
	TextView statusText;

	List<SquareAdapter> squareAdapters;

	@Bean
	State state;

//	@Bean
//	CallGamesServiceTask callGamesServiceTask;

	/**
	 * The view to show the ad.
	 */
	private AdView adView;

	private OnItemClickListener onFieldClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
			int checkedRadioButtonId = radioGroupTiles.getCheckedRadioButtonId();

			final Board board = state.getBoard();
			if (board == null) {
				errorMessage("Internal fault, no board");
				return;
			}

			final SquareAdapter adapter = (SquareAdapter) adapterView.getAdapter();
			final Field destinationField = board.getField(adapter.getSquarePosition(), position);

			if (checkedRadioButtonId != -1) {
				RadioButton checkedRadioButton = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
				final Integer verdi = Integer.parseInt(checkedRadioButton.getText().toString());


				// se om denne id'en er brukt andre steder
				final Field usedField = board.findCellById(checkedRadioButtonId);

				// ikke lov å skrive til et låst field
				if (destinationField == null || !destinationField.isLocked()) {


					// hvis id'en er i bruk, frigi tallet, så lenge det ikke er samme tall som skal plasseres
					if (usedField != null) {
						int square = board.getSquare(usedField);
						board.freeCell(usedField);
						if (square >= 0)
							squareAdapters.get(square).redrawField(board);
						if (destinationField == null || !usedField.getId().equals(destinationField.getId()))
							setButtonViewState(usedField.getId(),true);
					}

					// Finnes cellen fra før ?
					if (destinationField != null) {
						if (destinationField.getId() != null) {

							// frigi tallet som er i dette fieldet, så lenge det ikke er samme tall som skal plasseres

							if (!destinationField.getId().equals(checkedRadioButtonId))
								setButtonViewState(destinationField.getId(),true);

							destinationField.setValue(verdi);
							destinationField.setId(checkedRadioButtonId);
						}
					} else {
						board.storeFieldCell(adapter.getSquarePosition(), position, new Field(verdi, false, checkedRadioButtonId));
					}
					setButtonViewState(checkedRadioButtonId, false);
					adapter.redrawField(board);
				}
				radioGroupTiles.clearCheck();
			} else {
				// Klikk uten at en radiobutton (tile) er valgt
				if (destinationField != null && !destinationField.isLocked()) {
					// Det er noe i cella, og det er ikke låst, da frigir vi det
					board.freeCell(destinationField);
					adapter.redrawField(board);
					if (destinationField.getId() != null)
						setButtonViewState(destinationField.getId(), true);
				}

			}
		}
	};

	private void errorMessage(String msg) {
	}

	private void setButtonViewState(int id, boolean state) {
		RadioButton radioButton = (RadioButton) getActivity().findViewById(id);
		if (radioButton != null) {
			if (state)
				radioButton.setVisibility(View.VISIBLE);
			else
				radioButton.setVisibility(View.INVISIBLE);
		}
	}

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-9396891120929103/5003937078";

	List<SquareGridView> squareList;
	List<RadioButton> tiles;

	@AfterViews
	protected void init() {
		squareList = new ArrayList<SquareGridView>();
		squareList.add(square1);
		squareList.add(square2);
		squareList.add(square3);
		squareList.add(square4);
		squareList.add(square5);
		squareList.add(square6);
		squareList.add(square7);
		squareList.add(square8);
		squareList.add(square9);

		squareAdapters = new ArrayList<SquareAdapter>();
		for (int i = 0; i < Constants.NUMBER_OF_SQUARES; i++) {
			final SquareAdapter squareAdapter = new SquareAdapter(getActivity(),i, state.widthPixels);
			squareAdapters.add(squareAdapter);
			final SquareGridView squareGridView = squareList.get(i);
			squareGridView.setAdapter(squareAdapter);
			squareGridView.setOnItemClickListener(onFieldClickListener);
		}

		createAd();

		buttonSubmit.setOnClickListener(submitOnClickListener);

		if (state != null && state.isFilled())
			initializeBoard(state.getBoard());
		else
			Log.d(Constants.TAG,"BoardFragment init() har state!");
	}

	private View.OnClickListener submitOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Log.e(Constants.TAG, "buttonSubmit kallt");
			List<Move> moves = beregnMoves(state.getBoard());
			Round round = new Round();
			round.setMoves(moves);

			(new GamesServiceClient()).playRound(state.isOriginalPlayer ? state.mainPlayerAuth : state.otherPlayerAuth,
					state.gameId,round);
		}
	};

	private List<Move> beregnMoves(Board board) {
		List<Move> moves = new ArrayList<Move>();

		for (int x = 0; x < Constants.BOARD_WIDTH; x++)
			for (int y = 0; y < Constants.BOARD_HEIGHT; y++) {
				final Field field = board.getCell(x, y);
				if (field != null && !field.isLocked())
					moves.add(new Move(x,y,field.getValue()));
			}

		return moves;
	}

	private void createAd() {
		adView = new AdView(getActivity());
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId(AD_UNIT_ID);

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layoutParams.addRule(RelativeLayout.BELOW, R.id.buttons);
		adView.setLayoutParams(layoutParams);

		// Add the AdView to the view hierarchy. The view will have no size
		// until the ad is loaded.
		notBoard.addView(adView);

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CDE79EE5CEEDA4B6DA1F2212CD03C09F")
				.build();

		// Start loading the ad in the background.
		adView.loadAd(adRequest);
	}

	@Subscribe
	public void handleFetcheGame(FetcheGameEvent fetcheGameEvent) {
		Log.d(Constants.TAG,"handleFetcheGame kallt");

		(new GamesServiceClient()).getGame(state.isOriginalPlayer ? state.otherPlayerAuth : state.mainPlayerAuth,
				state.gameId);
		state.isOriginalPlayer = !state.isOriginalPlayer;
	}

	@Subscribe
	public void handleGameFetched(GameFetchedEvent gameFetchedEvent) {
		Game game = gameFetchedEvent.getGame();

		if (game != null) {
			initializeBoard(game.getBoard());

			state.setAvailablePieces(game.getAvailablePieces());
			state.gameId = game.getId();
			state.opponent = game.getOpponentUserId();
			state.difficulty = game.getDifficulty();
			state.status = game.getStatus();
			state.currentPlayer = game.getCurrentPlayer();
			state.score = game.getScore();
			state.opponentScore = game.getOpponentScore();

			oppdaterStatusText(state);
			initializeTiles(game.getAvailablePieces());
		} else {
			// todo: si ifra til brukeren om at lasting feilet
		}
	}

	private void oppdaterStatusText(State state) {
		StringBuilder sb = new StringBuilder();
		sb.append(state.currentPlayer).append(":").append(state.score).append("\n");
		sb.append(state.opponent).append(":").append(state.opponentScore).append("\n");
		statusText.setText(sb.toString());
	}

	private void initializeTiles(List<Integer> availablePieces) {
		tiles = new ArrayList<RadioButton>();
		tiles.add(tile1);
		tiles.add(tile2);
		tiles.add(tile3);
		tiles.add(tile4);
		tiles.add(tile5);
		tiles.add(tile6);
		tiles.add(tile7);

		int i = 0;
		for (RadioButton radioButton : tiles) {
			if (i < availablePieces.size()) {
				radioButton.setText(Integer.toString(availablePieces.get(i++)));
				radioButton.setVisibility(View.VISIBLE);
			} else
				radioButton.setVisibility(View.GONE);
		}
	}

	private void initializeBoard(List<Integer> boardNumbers) {
		if (boardNumbers == null || boardNumbers.size() < (Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH))
			return;

		final Board board = new Board();
		board.populateIntitialNumbers(boardNumbers);
		state.setBoard(board);

		initializeBoard(board);
	}

	private void initializeBoard(Board board) {
		if (board == null)
			return;
		for (SquareAdapter squareAdapter : squareAdapters) {
			squareAdapter.redrawField(board);
		}
	}

	@Override
	public void onResume() {
		Log.i(Constants.TAG, "BoardFragment:onResume() kallt");
		super.onResume();
		BusProvider.getInstance().register(this);

		if (state != null && state.isFilled())
			initializeBoard(state.getBoard());
//		else
//			callGamesServiceTask.initializeGame();

		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		Log.i(Constants.TAG, "BoardFragment:onPause() kallt");
		super.onPause();
		BusProvider.getInstance().unregister(this);
		if (adView != null) {
			adView.pause();
		}
	}

	/**
	 * Called before the activity is destroyed.
	 */
	@Override
	public void onDestroy() {
		Log.i(Constants.TAG, "BoardFragment:onDestroy() kallt");
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
