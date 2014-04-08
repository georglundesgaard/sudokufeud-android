package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;
import org.androidannotations.annotations.EBean;

import java.io.Serializable;

@EBean(scope = EBean.Scope.Singleton)
public class State extends SavedState {

	public boolean isFilled() {
		return super.isFilled();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
