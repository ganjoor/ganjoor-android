package net.ganjoor.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;

import net.ganjoor.R;
import net.ganjoor.ui.fragment.BookmarkFragment;
import net.ganjoor.ui.fragment.DashboardFragment;
import net.ganjoor.ui.fragment.SearchFragment;
import net.ganjoor.ui.fragment.SettingsFragment;

public class FragmentAdapter implements FragmentNavigatorAdapter {
    private static final String TABS[] = {"dashboard", "bookmark", "search", "settings"};

    @Override
    public Fragment onCreateFragment(int position) {
        Fragment mFragment = null;
        switch (position) {
            case 0:
                mFragment = new DashboardFragment().newInstance();
                break;
            case 1:
                mFragment = new BookmarkFragment().newInstance(R.string.title_bookmark);
                break;
            case 2:
                mFragment = new SearchFragment().newInstance(R.string.title_search);
                break;
            case 3:
                mFragment = new SettingsFragment().newInstance(R.string.title_settings);
                break;
        }
        return mFragment;
    }

    @Override
    public String getTag(int position) {
        return TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
