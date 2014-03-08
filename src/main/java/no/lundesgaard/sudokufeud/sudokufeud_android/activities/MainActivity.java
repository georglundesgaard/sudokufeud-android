package no.lundesgaard.sudokufeud.sudokufeud_android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.googlecode.androidannotations.annotations.*;
import no.lundesgaard.sudokufeud.sudokufeud_android.adapters.FieldAdapter;
import no.lundesgaard.sudokufeud.sudokufeud_android.listeners.OnFieldClickListener;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import no.lundesgaard.sudokufeud.sudokufeud_android.views.SquareGridView;

@EActivity(R.layout.main)
public class MainActivity extends Activity {

    @ViewById
    SquareGridView square1, square2, square3, square4, square5,
            square6, square7, square8, square9;

    @ViewById
    Button tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;

    public static String tileClicked;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //OnTileClickedListener onTileClickedListener = new OnTileClickedListener();
    }

    @AfterViews
    void bindAdapters() {
        FieldAdapter fieldAdapter = new FieldAdapter(this);
        square1.setAdapter(fieldAdapter);
        square2.setAdapter(fieldAdapter);
        square3.setAdapter(fieldAdapter);
        square4.setAdapter(fieldAdapter);
        square5.setAdapter(fieldAdapter);
        square6.setAdapter(fieldAdapter);
        square7.setAdapter(fieldAdapter);
        square8.setAdapter(fieldAdapter);
        square9.setAdapter(fieldAdapter);

        OnFieldClickListener OnFieldClickListener = new OnFieldClickListener();
        square1.setOnItemClickListener(OnFieldClickListener);
        square2.setOnItemClickListener(OnFieldClickListener);
        square3.setOnItemClickListener(OnFieldClickListener);
        square4.setOnItemClickListener(OnFieldClickListener);
        square5.setOnItemClickListener(OnFieldClickListener);
        square6.setOnItemClickListener(OnFieldClickListener);
        square7.setOnItemClickListener(OnFieldClickListener);
        square8.setOnItemClickListener(OnFieldClickListener);
        square9.setOnItemClickListener(OnFieldClickListener);
    }

    @Click({R.id.tile1, R.id.tile2, R.id.tile3, R.id.tile4, R.id.tile5,
            R.id.tile6, R.id.tile7, R.id.tile8, R.id.tile9})
    void tileClicked(View clickedTile) {
        tileClicked = ((Button) clickedTile).getText().toString();
    }

    // TODO: Find out how to do it this way
    /*@ItemClick({R.id.square1, R.id.square2, R.id.square3, R.id.square4, R.id.square5,
            R.id.square6, R.id.square7, R.id.square8, R.id.square9})
    void fieldClicked(TextView fieldView) {
        fieldView.setText(tileClicked);
    }*/
}
