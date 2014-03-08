package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.view.View;
import android.widget.Button;

public class OnTileClickedListener implements View.OnClickListener {

    public static String tileClicked;

    @Override
    public void onClick(View v) {
        Button tile = (Button) v;
        tileClicked = tile.getText().toString();
        tile.setSelected(true);
    }
}
