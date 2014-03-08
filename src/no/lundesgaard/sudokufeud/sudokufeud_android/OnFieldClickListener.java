package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class OnFieldClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) view).setText(OnTileClickedListener.tileClicked);
    }
}
