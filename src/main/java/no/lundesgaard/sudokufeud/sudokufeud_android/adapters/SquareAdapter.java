package no.lundesgaard.sudokufeud.sudokufeud_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Field;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;

public class SquareAdapter extends BaseAdapter {

    private Context context;
	private int squarePosition;
	private Board board;

	public SquareAdapter(Context context, int squarePosition) {
		this.context = context;
		this.squarePosition = squarePosition;
	}

	public int getSquarePosition() {
		return squarePosition;
	}

	@Override
	public int getCount() {
		return Constants.SQUARE_SIZE;
	}

	@Override
	public Field getItem(int position) {
		if (board != null)
	        return board.getField(squarePosition, position);
		else
			return null;
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

		if (board != null) {
			Field field = board.getField(squarePosition, position);
			if (field != null) {
				Integer fieldValue = field.getValue();

				if (field.isLocked()) {
					viewHolder.textView.setBackground(context.getResources().getDrawable(R.drawable.field_gradient));
				}

				if (fieldValue != null) {
					viewHolder.textView.setText(Integer.toString(fieldValue));
				}
				else {
					viewHolder.textView.setText("");
				}
			} else {
				viewHolder.textView.setText("");
			}
		}

        return convertView;
    }

    public void redrawField(Board board) {
		this.board = board;
		notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView textView;
    }
}
