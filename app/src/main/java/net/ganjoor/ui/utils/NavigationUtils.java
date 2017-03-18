package net.ganjoor.ui.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;
import net.ganjoor.R;

/**
 * Created by arash on 3/18/17.
 */

public class NavigationUtils {

  private TextView mTargetTextView;

  public NavigationUtils(TextView mTargetTextView) {
    this.mTargetTextView = mTargetTextView;
  }

  public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_dashboard:
          mTargetTextView.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_bookmark:
          mTargetTextView.setText(R.string.title_bookmark);
          return true;
        case R.id.navigation_search:
          mTargetTextView.setText(R.string.title_search);
          return true;
        case R.id.navigation_settings:
          mTargetTextView.setText(R.string.title_settings);
          return true;
      }
      return false;
    }

  };
}
