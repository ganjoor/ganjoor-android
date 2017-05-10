package net.ganjoor.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import net.ganjoor.R;
import net.ganjoor.model.Poem;
import net.ganjoor.model.VerseCombine;
import net.ganjoor.model.VersePojo;
import net.ganjoor.service.APIServices;
import net.ganjoor.service.RetrofitUtils;
import net.ganjoor.utils.AppUtils;
import net.ganjoor.utils.GridSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VerseActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Poem poem;
    private FastItemAdapter<VerseCombine> fastItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        poem = getIntent().getParcelableExtra("poem");
        String poetName = getIntent().getStringExtra("poetName");
        title.setText(poetName + " - " + poem.getTitle());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, AppUtils.dpToPx(1, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getVerseByPoem(poem.getId());
                    }
                }
        );
        //create our FastAdapter which will manage everything
        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withSelectable(true);
        fastItemAdapter.withOnPreClickListener(new FastAdapter.OnClickListener<VerseCombine>() {
            @Override
            public boolean onClick(View v, IAdapter<VerseCombine> adapter, VerseCombine item, int position) {
                // consume otherwise radio/checkbox will be deselected
                return true;
            }
        });
        fastItemAdapter.withItemEvent(new VerseCombine.CheckBoxClickEvent());
        recyclerView.setAdapter(fastItemAdapter);
    }

    private void getVerseByPoem(String poemId) {
        swipeRefreshLayout.setRefreshing(true);
        APIServices apiServices = RetrofitUtils.getRetrofit().create(APIServices.class);

        Call<VersePojo> call = apiServices.verses(poemId);
        call.enqueue(new Callback<VersePojo>() {
            @Override
            public void onResponse(Call<VersePojo> call, Response<VersePojo> response) {
                swipeRefreshLayout.setRefreshing(false);
                VersePojo versePojo = response.body();
                fastItemAdapter.clear();
                fastItemAdapter.add(AppUtils.getVerseCombine(versePojo.getVerses()));
            }

            @Override
            public void onFailure(Call<VersePojo> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getVerseByPoem(poem.getId());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_share).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
