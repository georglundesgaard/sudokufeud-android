package no.lundesgaard.sudokufeud.sudokufeud_android.ui;

import java.util.ArrayList;
import java.util.List;

import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import no.lundesgaard.sudokufeud.sudokufeud_android.adapters.SquareAdapter;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.BoardFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.tasks.CallGamesServiceTask;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.BusProvider;
import no.lundesgaard.sudokufeud.sudokufeud_android.views.RelativeRadioGroup;
import no.lundesgaard.sudokufeud.sudokufeud_android.views.SquareGridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.squareup.otto.Subscribe;

@EFragment(R.layout.fragment_board)
public class BoardFragment extends Fragment {

    @ViewById
    SquareGridView square1, square2, square3, square4, square5,
            square6, square7, square8, square9;

    @ViewById
    RelativeLayout notBoard;

    @ViewById
    RelativeRadioGroup radioGroupTiles;

    List<SquareAdapter> squareAdapters;

    @Bean
    CallGamesServiceTask callGamesServiceTask;

    /** The view to show the ad. */
    private AdView adView;

    private OnItemClickListener onFieldClickListener = new OnItemClickListener() {                
    	public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            int checkedRadioButtonId = radioGroupTiles.getCheckedRadioButtonId();
            RadioButton checkedRadioButton = (RadioButton) getActivity().findViewById(checkedRadioButtonId);

            ((SquareAdapter) adapterView.getAdapter()).setFieldValue(position,
                    Integer.parseInt(checkedRadioButton.getText().toString()));            
        }
    };

    /* Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "ca-app-pub-9396891120929103/5003937078";

    @AfterViews
    protected void init() {
        squareAdapters = new ArrayList<SquareAdapter>();
        for (int i = 0; i < 9; i++) {
            SquareAdapter squareAdapter = new SquareAdapter(getActivity());
            squareAdapters.add(squareAdapter);
        }

        square1.setAdapter(squareAdapters.get(0));
        square2.setAdapter(squareAdapters.get(1));
        square3.setAdapter(squareAdapters.get(2));
        square4.setAdapter(squareAdapters.get(3));
        square5.setAdapter(squareAdapters.get(4));
        square6.setAdapter(squareAdapters.get(5));
        square7.setAdapter(squareAdapters.get(6));
        square8.setAdapter(squareAdapters.get(7));
        square9.setAdapter(squareAdapters.get(8));

        square1.setOnItemClickListener(onFieldClickListener);
        square2.setOnItemClickListener(onFieldClickListener);
        square3.setOnItemClickListener(onFieldClickListener);
        square4.setOnItemClickListener(onFieldClickListener);
        square5.setOnItemClickListener(onFieldClickListener);
        square6.setOnItemClickListener(onFieldClickListener);
        square7.setOnItemClickListener(onFieldClickListener);
        square8.setOnItemClickListener(onFieldClickListener);
        square9.setOnItemClickListener(onFieldClickListener);

        createAd();
    }

    private void createAd() {
        adView = new AdView(getActivity());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.radioGroupTiles);
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
    public void setFieldValues(BoardFetchedEvent boardFetchedEvent) {
        List<Integer> board = boardFetchedEvent.getBoard();

        for (int i = 0; i < squareAdapters.size(); i++) {
            List<Integer> squareValues = new ArrayList<Integer>();
            int start = (i/3)*18 + i*3;
            List<Integer> squareRow1 = board.subList(start, start + 3);
            List<Integer> squareRow2 = board.subList(start + 9, start + 12);
            List<Integer> squareRow3 = board.subList(start + 18, start + 21);
            squareValues.addAll(squareRow1);
            squareValues.addAll(squareRow2);
            squareValues.addAll(squareRow3);
            SquareAdapter squareAdapter = squareAdapters.get(i);
            squareAdapter.setFieldValues(squareValues);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        callGamesServiceTask.initializeGame();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        if (adView != null) {
            adView.pause();
        }
    }

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
