package no.lundesgaard.sudokufeud.sudokufeud_android.util;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.BaseEvent;

public class BusProvider {

    private static final Bus BUS = new Bus();

    private BusProvider() {}

    public static Bus getInstance() {
        return BUS;
    }

	private final static Handler handler = new Handler(Looper.getMainLooper());

	public static void updateUi(final BaseEvent baseEvent) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			BUS.post(baseEvent);
		} else {
			handler.post(new Runnable() {
				@Override
				public void run() {
					BUS.post(baseEvent);
				}
			});
		}
	}
}
