package no.lundesgaard.sudokufeud.sudokufeud_android.ui;

import android.app.Fragment;
import android.widget.EditText;
import no.lundesgaard.sudokufeud.sudokufeud_android.R;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_log_in)
public class LogInFragment extends Fragment {

    @ViewById
    EditText editTextUsername, editTextPassword;

}
