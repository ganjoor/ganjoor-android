package net.ganjoor.ui.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.aspsine.fragmentnavigator.FragmentNavigator;

import net.ganjoor.R;

public class NavigationUtils {

    private FragmentNavigator fragmentNavigator;

    public NavigationUtils(FragmentNavigator fragmentNavigator) {
        this.fragmentNavigator = fragmentNavigator;
        fragmentNavigator.showFragment(0);
    }


    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragmentNavigator.showFragment(0);
                    break;
                case R.id.navigation_bookmark:
                    fragmentNavigator.showFragment(1);
                    break;
                case R.id.navigation_search:
                    fragmentNavigator.showFragment(2);
                    break;
                case R.id.navigation_settings:
                    fragmentNavigator.showFragment(3);
                    break;
            }
            return true;
        }

    };
}
