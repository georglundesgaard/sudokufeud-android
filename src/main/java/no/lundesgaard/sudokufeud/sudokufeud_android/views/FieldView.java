package no.lundesgaard.sudokufeud.sudokufeud_android.views;


import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;

@EViewGroup(R.layout.field)
public class FieldView extends RelativeLayout {

    public FieldView(Context context) {
        super(context);
    }
}
