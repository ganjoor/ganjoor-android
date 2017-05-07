package net.ganjoor.ui;


import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import net.ganjoor.R;
import net.ganjoor.model.Poem;
import net.ganjoor.model.PoemPojo;
import net.ganjoor.model.Poet;
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

public class PoemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.name_poet)
    TextView namePoet;
    @BindView(R.id.description_poet)
    TextView descriptionPoet;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Poet poet;
    private Typeface typeface;
    private FastItemAdapter<Poem> fastItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem);
        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/DroidNaskh-Regular.ttf");
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        poet = getIntent().getParcelableExtra("poet");
        namePoet.setText(poet.getName());
        descriptionPoet.setText(poet.getDescription());
        initCollapsingToolbar(poet.getName());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, AppUtils.dpToPx(10, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withOnClickListener(new FastAdapter.OnClickListener<Poem>() {
            @Override
            public boolean onClick(View v, IAdapter<Poem> adapter, Poem poem, int position) {
                Intent intent = new Intent(PoemActivity.this, VerseActivity.class);
                intent.putExtra("poem", poem);
                intent.putExtra("poetName", poet.getName());
                startActivity(intent);
                return false;
            }
        });
        recyclerView.setAdapter(fastItemAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getPoemByCategory(poet.getCategoryId());
                    }
                }
        );
    }

    private void getPoemByCategory(String categoryId) {
        swipeRefreshLayout.setRefreshing(true);
        APIServices apiServices = RetrofitUtils.getRetrofit().create(APIServices.class);
        Call<PoemPojo> call = apiServices.poems(categoryId);
        call.enqueue(new Callback<PoemPojo>() {
            @Override
            public void onResponse(Call<PoemPojo> call, Response<PoemPojo> response) {
                swipeRefreshLayout.setRefreshing(false);
                PoemPojo poemPojo = response.body();
                fastItemAdapter.clear();
                fastItemAdapter.add(poemPojo.getPoems());
            }

            @Override
            public void onFailure(Call<PoemPojo> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();

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
        getPoemByCategory(poet.getCategoryId());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
