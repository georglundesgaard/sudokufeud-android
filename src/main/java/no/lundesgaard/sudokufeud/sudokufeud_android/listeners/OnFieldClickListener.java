package no.lundesgaard.sudokufeud.sudokufeud_android.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import no.lundesgaard.sudokufeud.sudokufeud_android.activities.MainActivity;

public class OnFieldClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) view).setText(MainActivity.tileClicked);
    }
}
