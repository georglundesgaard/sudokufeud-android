package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity {

    private String tileClicked;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageAdapter imageAdapter = new ImageAdapter(this);
        SquareGridView square1 = (SquareGridView) findViewById(R.id.square1);
        SquareGridView square2 = (SquareGridView) findViewById(R.id.square2);
        SquareGridView square3 = (SquareGridView) findViewById(R.id.square3);
        SquareGridView square4 = (SquareGridView) findViewById(R.id.square4);
        SquareGridView square5 = (SquareGridView) findViewById(R.id.square5);
        SquareGridView square6 = (SquareGridView) findViewById(R.id.square6);
        SquareGridView square7 = (SquareGridView) findViewById(R.id.square7);
        SquareGridView square8 = (SquareGridView) findViewById(R.id.square8);
        SquareGridView square9 = (SquareGridView) findViewById(R.id.square9);

        Button tile1 = (Button) findViewById(R.id.tile1);
        Button tile2 = (Button) findViewById(R.id.tile2);
        Button tile3 = (Button) findViewById(R.id.tile3);
        Button tile4 = (Button) findViewById(R.id.tile4);
        Button tile5 = (Button) findViewById(R.id.tile5);
        Button tile6 = (Button) findViewById(R.id.tile6);
        Button tile7 = (Button) findViewById(R.id.tile7);
        Button tile8 = (Button) findViewById(R.id.tile8);
        Button tile9 = (Button) findViewById(R.id.tile9);

        OnTileClickedListener onTileClickedListener = new OnTileClickedListener();

        tile1.setOnClickListener(onTileClickedListener);
        tile2.setOnClickListener(onTileClickedListener);
        tile3.setOnClickListener(onTileClickedListener);
        tile4.setOnClickListener(onTileClickedListener);
        tile5.setOnClickListener(onTileClickedListener);
        tile6.setOnClickListener(onTileClickedListener);
        tile7.setOnClickListener(onTileClickedListener);
        tile8.setOnClickListener(onTileClickedListener);
        tile9.setOnClickListener(onTileClickedListener);

        square1.setAdapter(imageAdapter);
        square2.setAdapter(imageAdapter);
        square3.setAdapter(imageAdapter);
        square4.setAdapter(imageAdapter);
        square5.setAdapter(imageAdapter);
        square6.setAdapter(imageAdapter);
        square7.setAdapter(imageAdapter);
        square8.setAdapter(imageAdapter);
        square9.setAdapter(imageAdapter);

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
}
