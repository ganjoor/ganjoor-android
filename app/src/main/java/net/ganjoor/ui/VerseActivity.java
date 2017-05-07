package net.ganjoor.ui;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import net.ganjoor.R;
import net.ganjoor.adapter.VerseAdapter;
import net.ganjoor.model.Poem;
import net.ganjoor.model.Verse;
import net.ganjoor.model.VersePojo;
import net.ganjoor.service.APIServices;
import net.ganjoor.service.RetrofitUtils;
import net.ganjoor.utils.AppUtils;
import net.ganjoor.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VerseActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.name_poet)
    TextView namePoet;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Poem poem;
    private VerseAdapter adapter;
    private List<Verse> verseList = new ArrayList<>();
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);
        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/DroidNaskh-Regular.ttf");
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        poem = getIntent().getParcelableExtra("poem");
        String poetName = getIntent().getStringExtra("poetName");
        namePoet.setText(poetName + " - " + poem.getTitle());
        initCollapsingToolbar(poetName + " - " + poem.getTitle());
        adapter = new VerseAdapter(this, verseList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, AppUtils.dpToPx(1, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getVerseByPoem(poem.getId());
                    }
                }
        );
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
                verseList.clear();
                verseList.addAll(versePojo.getVerses());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<VersePojo> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     *
     * @param name
     */
    private void initCollapsingToolbar(final String name) {
        collapsingToolbar.setTitle(" ");
        collapsingToolbar.setCollapsedTitleTypeface(typeface);
        collapsingToolbar.setExpandedTitleTypeface(typeface);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(name);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
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
}
