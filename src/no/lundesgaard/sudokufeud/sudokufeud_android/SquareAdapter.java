package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class SquareAdapter extends BaseAdapter {

    private ArrayList<Button> squares;

    private Context context;

    public SquareAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return squares.size();
    }

    @Override
    public Object getItem(int position) {
       return squares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
