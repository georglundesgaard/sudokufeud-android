package no.lundesgaard.sudokufeud.sudokufeud_android.ui;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.*;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import no.lundesgaard.sudokufeud.sudokufeud_android.State;
import no.lundesgaard.sudokufeud.sudokufeud_android.adapters.SquareAdapter;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.GameFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Cell;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;
import no.lundesgaard.sudokufeud.sudokufeud_android.tasks.CallGamesServiceTask;
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
	RadioGroup radioGroupTiles;
//    RelativeRadioGroup radioGroupTiles;

	List<SquareAdapter> squareAdapters;

	@Bean
	State state;

	@Bean
	CallGamesServiceTask callGamesServiceTask;

	/**
	 * The view to show the ad.
	 */
	private AdView adView;

	private OnItemClickListener onFieldClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
			int checkedRadioButtonId = radioGroupTiles.getCheckedRadioButtonId();
			if (checkedRadioButtonId != -1) {
				final SquareAdapter adapter = (SquareAdapter) adapterView.getAdapter();
				RadioButton checkedRadioButton = (RadioButton) getActivity().findViewById(checkedRadioButtonId);
				final Integer verdi = Integer.parseInt(checkedRadioButton.getText().toString());
				final Board board = state.getBoard();

				final Cell destinationCell = board.getFieldCell(adapter.getSquarePosition(), position);

				// ikke lov å skrive til en låst celle
				if (destinationCell == null || !destinationCell.isLocked()) {

					// se om denne id'en er brukt andre steder
					final Cell usedCell = board.findCellById(checkedRadioButtonId);

					// hvis så, frigi tallet
					if (usedCell != null) {
						setButtonViewState(usedCell.getId(),true);
						int field = board.getField(usedCell);
						if (field >= 0)
							squareAdapters.get(field).redrawField(board);
						board.freeCell(usedCell);
					}

					// Finnes cellen fra før ?
					if (destinationCell != null) {
						if (destinationCell.getId() != null) {

							// frigi tallet som er i denne cella
							setButtonViewState(destinationCell.getId(),true);

							destinationCell.setValue(verdi);
						}
					} else {
						board.storeFieldCell(adapter.getSquarePosition(), position, new Cell(verdi, false, checkedRadioButtonId));
					}
					setButtonViewState(checkedRadioButtonId, false);
					adapter.redrawField(board);
//					initializeBoard(board);
				}
/*					final Cell usedCell = board.findCellById(checkedRadioButtonId);


				boolean updated = adapter.setField(position,
						Integer.parseInt(verdi));

				if (updated)
					checkedRadioButton.setVisibility(View.GONE);
*/
			}
		}
	};

	private void setButtonViewState(int id, boolean state) {
		RadioButton radioButton = (RadioButton) getActivity().findViewById(id);
		if (radioButton != null) {
			if (state)
				radioButton.setVisibility(View.VISIBLE);
			else
				radioButton.setVisibility(View.GONE);
		}
	}

	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-9396891120929103/5003937078";

	List<SquareGridView> squareList;

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
			final SquareAdapter squareAdapter = new SquareAdapter(getActivity(),i);
			squareAdapters.add(squareAdapter);
			final SquareGridView squareGridView = squareList.get(i);
			squareGridView.setAdapter(squareAdapter);
			squareGridView.setOnItemClickListener(onFieldClickListener);
		}

		createAd();

		if (state != null && state.isFilled())
			initializeBoard(state.getBoard());
		else
			Log.d(Constants.TAG,"BoardFragment init() har state!");
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
	public void handleGameFetched(GameFetchedEvent gameFetchedEvent) {
		Game game = gameFetchedEvent.getGame();

		if (game != null) {
			initializeBoard(game.getBoard());

			state.setAvailablePieces(game.getAvailablePieces());

			initializeTiles(game.getAvailablePieces());
		} else {
			// todo: Klag på Georg
		}
	}

	private void initializeTiles(List<Integer> availablePieces) {
		tile1.setText(availablePieces.get(0) + "");
		tile2.setText(availablePieces.get(1) + "");
		tile3.setText(availablePieces.get(2) + "");
		tile4.setText(availablePieces.get(3) + "");
		tile5.setText(availablePieces.get(4) + "");
		tile6.setText(availablePieces.get(5) + "");
		tile7.setText(availablePieces.get(6) + "");

		tile1.setVisibility(View.VISIBLE);
		tile2.setVisibility(View.VISIBLE);
		tile3.setVisibility(View.VISIBLE);
		tile4.setVisibility(View.VISIBLE);
		tile5.setVisibility(View.VISIBLE);
		tile6.setVisibility(View.VISIBLE);
		tile7.setVisibility(View.VISIBLE);
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
		else
			callGamesServiceTask.initializeGame();

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
