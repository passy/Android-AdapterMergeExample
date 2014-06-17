package twitter.example.com.adaptermerge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filterable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTextChanged;

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
    private MergedAdapter<ArrayAdapter<String>> mMergedHashtagAdapter;
    private MergedAdapter<ArrayAdapter<String>> mUsersAdapter;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setupAdapters();
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    private void setupAdapters() {
        final ArrayAdapter<String> hashtagAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_dropdown_item_1line, HASHTAGS);
        final ArrayAdapter<String> featuredAdapter = new ArrayAdapter<>(
                getActivity(), R.layout.featured_dropdown_item_1line, FEATURED_HASHTAGS);

        mMergedHashtagAdapter = new MergedAdapter<>();
        mMergedHashtagAdapter.setAdapters(
                featuredAdapter,
                hashtagAdapter
        );

        mUsersAdapter = new MergedAdapter<>();
        mUsersAdapter.setAdapters(new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_dropdown_item_1line, USERS));
    }

    @OnTextChanged(R.id.text)
    void onTextChanged(CharSequence text) {
        if (text.toString().startsWith("#")) {
            mEditText.setAdapter(mMergedHashtagAdapter);
        } else if (text.toString().startsWith("@")) {
            mEditText.setAdapter(mUsersAdapter);
        }
    }
}
