package no.lundesgaard.sudokufeud.sudokufeud_android.tasks;

import android.content.Context;
import no.lundesgaard.sudokufeud.sudokufeud_android.events.BaseEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.BusProvider;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

@EBean
public class BaseTask {

    @RootContext
    protected Context context;

    @UiThread
    protected void updateUi(BaseEvent baseEvent) {
        BusProvider.getInstance().post(baseEvent);
    }
}
