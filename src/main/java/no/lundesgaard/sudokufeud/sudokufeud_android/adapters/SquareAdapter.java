package no.lundesgaard.sudokufeud.sudokufeud_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;

import java.util.ArrayList;
import java.util.List;

public class SquareAdapter extends BaseAdapter {

    private Context context;

    List<Integer> fieldValues;

    public SquareAdapter(Context c) {
        context = c;
        fieldValues = new ArrayList<Integer>();
    }

    public int getCount() {
        return fieldValues.size();
    }

    public Integer getItem(int position) {
        return fieldValues.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new TextView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.field, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.field);
            viewHolder.textView.setLayoutParams(new GridView.LayoutParams(100, 100));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Integer fieldValue = fieldValues.get(position);
        if (fieldValue != null) {
            viewHolder.textView.setText(fieldValue + "");
        }
        else {
            viewHolder.textView.setText("");
        }

        return convertView;
    }

    public void setFieldValues(List<Integer> fieldList) {
        fieldValues.clear();
        fieldValues.addAll(fieldList);
        notifyDataSetChanged();
    }

    public void setFieldValue(int position, Integer value) {
    	fieldValues.set(position, value);
    	notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView textView;
    }
}
