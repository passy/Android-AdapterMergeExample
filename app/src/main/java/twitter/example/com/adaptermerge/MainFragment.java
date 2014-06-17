package twitter.example.com.adaptermerge;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainFragment extends Fragment {
    // Used when a phrase starts with a #
    private static final String[] HASHTAGS = new String[] {
        "belgium", "france", "italy", "germany", "spain"
    };

    // Should override HASHTAGS and use a special view
    private static final String[] FEATURED_HASHTAGS = new String[] {
        "germany", "usa", "uk"
    };

    // used when a phrase starts with an @
    private static final String[] USERS = new String[] {
        "monchote", "pauto", "tomwoolway", "phuunet", "stephenplusplus", "sindresorhus",
        "addyosmani"
    };

    private static final String[] NORMAL_COLUMNS = new String[] {
        "_id", "item"
    };

    @InjectView(R.id.text)
    AutoCompleteTextView mEditText;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        setupViews();
        return rootView;
    }

    private void setupViews() {
        final MatrixCursor c = new MatrixCursor(NORMAL_COLUMNS);
        getActivity().startManagingCursor(c);

        for (int i = 0; i < HASHTAGS.length; i += 1) {
            c.addRow(new Object[] { i, HASHTAGS[i] });
        }


        final ArrayAdapter<String> adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, c, NORMAL_COLUMNS, new int[] { 0, R. })
                getActivity(), android.R.layout.simple_dropdown_item_1line, HASHTAGS);
        mEditText.setAdapter(adapter);
    }
}
