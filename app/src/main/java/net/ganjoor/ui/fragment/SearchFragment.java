package net.ganjoor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.ganjoor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment {
    private static final String INT_FRAGMENT = "INT_FRAGMENT";
    @BindView(R.id.messageTextView)
    TextView messageTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,
                container, false);
        ButterKnife.bind(this, view);
        @StringRes int text = getArguments().getInt(INT_FRAGMENT);
        messageTextView.setText(text);
        return view;
    }

    public Fragment newInstance(@StringRes int resourceId) {
        SearchFragment mFragment = new SearchFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(INT_FRAGMENT, resourceId);
        mFragment.setArguments(mBundle);
        return mFragment;
    }
}
