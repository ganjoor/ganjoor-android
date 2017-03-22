package net.ganjoor.ui.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import net.ganjoor.R;
import net.ganjoor.ui.fragment.BookmarkFragment;
import net.ganjoor.ui.fragment.DashboardFragment;
import net.ganjoor.ui.fragment.GeneralFragment;
import net.ganjoor.ui.fragment.SearchFragment;

public class NavigationUtils {

    private
    @IdRes
    int mContentFrameLayout;
    private Fragment mFragment;
    private FragmentManager mFragmentManager;

    public NavigationUtils(@IdRes int contentFrameLayout, FragmentManager fragmentManager) {
        this.mContentFrameLayout = contentFrameLayout;
        this.mFragmentManager = fragmentManager;
        mFragment = new GeneralFragment().newInstance(R.string.title_dashboard);
        doTransaction();
    }

    private void doTransaction() {
        if (mFragment != null) {
            mFragmentManager.beginTransaction()
                    .replace(mContentFrameLayout, mFragment).commit();
        }
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    mFragment = new DashboardFragment().newInstance(R.string.title_dashboard);
                    break;
                case R.id.navigation_bookmark:
                    mFragment = new BookmarkFragment().newInstance(R.string.title_bookmark);
                    break;
                case R.id.navigation_search:
                    mFragment = new SearchFragment().newInstance(R.string.title_search);
                    break;
                case R.id.navigation_settings:
                    mFragment = new GeneralFragment().newInstance(R.string.title_settings);
                    break;
            }
            doTransaction();
            return true;
        }

    };
}
