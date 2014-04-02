package no.lundesgaard.sudokufeud.sudokufeud_android.ui;

import android.os.Bundle;
import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;

import no.lundesgaard.sudokufeud.sudokufeud_android.SavedState;
import no.lundesgaard.sudokufeud.sudokufeud_android.State;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import org.androidannotations.annotations.*;

import android.app.Activity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@InstanceState
	SavedState savedState;

	@Bean
	State state;

	@Override
	public void onCreate(Bundle savedBundle) {
		super.onCreate(savedBundle);
		Log.i(Constants.TAG, "onCreate: savedState=" + savedState);
		if (savedState != null) {
			state.setOriginalBoard(savedState.getOriginalBoard());
			state.setCurrentBoard(savedState.getCurrentBoard());
		} else {
			if (savedBundle != null) {
				savedState = (State) savedBundle.get("savedState");
				if (savedState != null) {
					state.setCurrentBoard(savedState.getCurrentBoard());
					state.setOriginalBoard(savedState.getOriginalBoard());
					Log.i(Constants.TAG,"hentet ut savedstate fra bundle");
				} else
					Log.w(Constants.TAG, "onCreate: savedState var tom/manglet");
			}
		}
	}

	@Override
	public void onResume() {
		Log.i(Constants.TAG, "MainActivity:onResume() kallt");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i(Constants.TAG, "MainActivity:onPause() kallt");
		super.onPause();
	}

	/**
	 * Called before the activity is destroyed.
	 */
	@Override
	public void onDestroy() {
		Log.i(Constants.TAG, "MainActivity:onDestroy() kallt");
		if (savedState != null) {
			if (state != null) {
				savedState.setCurrentBoard(state.getCurrentBoard());
				savedState.setOriginalBoard(state.getOriginalBoard());
			}
		}
		super.onDestroy();
	}
}
