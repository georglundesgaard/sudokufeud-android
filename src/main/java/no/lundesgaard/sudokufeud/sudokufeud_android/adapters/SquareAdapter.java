package no.lundesgaard.sudokufeud.sudokufeud_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Field;

import java.util.ArrayList;
import java.util.List;

public class SquareAdapter extends BaseAdapter {

    private Context context;
	private int squarePosition;

	private List<Field> fields;

    public SquareAdapter(Context c, int squarePosition) {
        context = c;
		this.squarePosition = squarePosition;
		fields = new ArrayList<Field>();
    }

	public int getSquarePosition() {
		return squarePosition;
	}

	public int getCount() {
        return fields.size();
    }

    public Field getItem(int position) {
        return fields.get(position);
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

        Integer fieldValue = getItem(position).getValue();

        if (fields.get(position).isLocked()) {
            viewHolder.textView.setBackground(context.getResources().getDrawable(R.drawable.field_gradient));
        }

        if (fieldValue != null) {
            viewHolder.textView.setText(fieldValue + "");
        }
        else {
            viewHolder.textView.setText("");
        }

        return convertView;
    }

    public void setFields(List<Integer> fieldList) {
        fields.clear();
        for (int i = 0; i < fieldList.size(); i++) {
            Integer value = fieldList.get(i);
            boolean locked = (value != null);
            fields.add(new Field(value, locked, i));
        }
        notifyDataSetChanged();
    }

    public boolean setField(int position, Integer value) {
    	if (!getItem(position).isLocked()) {
            fields.set(position, new Field(value, false, position));
    	    notifyDataSetChanged();
            return true;
        }
        return false;
    }

    static class ViewHolder {
        TextView textView;
    }
}
