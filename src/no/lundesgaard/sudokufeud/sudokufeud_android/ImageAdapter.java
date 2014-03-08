package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    public ImageAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return mThumbIds.length;
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
            //textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //textView.setTextColor(android.R.color.black);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        } else {
            textView = (TextView) convertView;
        }
        //textView.setBackgroundColor(android.R.color.white);
        return textView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2
    };
}
