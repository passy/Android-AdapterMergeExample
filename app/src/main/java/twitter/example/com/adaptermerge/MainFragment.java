package twitter.example.com.adaptermerge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainFragment extends Fragment {
    private static final String[] HASHTAGS = new String[] {
         "belgium", "france", "italy", "germany", "spain"
    };

    private static final String[] FEATURED_HASHTAGS = new String[] {
         "germany", "usa", "uk"
    };

    private static final String[] USERS = new String[] {
         "monchote", "pauto", "tomwoolway", "phuunet", "stephenplusplus", "sindresorhus",
         "addyosmani"
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
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_dropdown_item_1line, HASHTAGS);
        mEditText.setAdapter(adapter);
    }
}
