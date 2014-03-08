package no.lundesgaard.sudokufeud.sudokufeud_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;

public class FieldAdapter extends BaseAdapter {

    private Context context;

    public FieldAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return 9;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new TextView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView textView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = (TextView) inflater.inflate(R.layout.field, null);
            textView.setLayoutParams(new GridView.LayoutParams(100, 100));
        } else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
