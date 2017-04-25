package net.ganjoor.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.aspsine.fragmentnavigator.FragmentNavigator;

import net.ganjoor.R;
import net.ganjoor.adapter.FragmentAdapter;
import net.ganjoor.ui.utils.NavigationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private FragmentNavigator fragmentNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), new FragmentAdapter(), R.id.contentFrameLayout);
        navigation.setOnNavigationItemSelectedListener(new NavigationUtils(fragmentNavigator)
                .mOnNavigationItemSelectedListener);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
