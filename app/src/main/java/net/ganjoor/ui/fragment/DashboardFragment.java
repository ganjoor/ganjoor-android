package net.ganjoor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ganjoor.R;
import net.ganjoor.adapter.PoetAdapter;
import net.ganjoor.model.Poet;
import net.ganjoor.model.PoetPojo;
import net.ganjoor.service.APIServices;
import net.ganjoor.service.RetrofitUtils;
import net.ganjoor.ui.PoemActivity;
import net.ganjoor.utils.AppUtils;
import net.ganjoor.utils.GridSpacingItemDecoration;
import net.ganjoor.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private PoetAdapter adapter;
    private List<Poet> poetList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,
                container, false);
        ButterKnife.bind(this, view);
        poetList = new ArrayList<>();
        adapter = new PoetAdapter(getActivity(), poetList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, AppUtils.dpToPx(10, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getPoets();
                    }
                }
        );
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PoemActivity.class);
                intent.putExtra("poet", poetList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }

    private void getPoets() {
        swipeRefreshLayout.setRefreshing(true);
        APIServices apiServices = RetrofitUtils.getRetrofit().create(APIServices.class);
        Call<PoetPojo> call = apiServices.poets();
        call.enqueue(new Callback<PoetPojo>() {
            @Override
            public void onResponse(Call<PoetPojo> call, Response<PoetPojo> response) {
                PoetPojo poetPojo = response.body();
                poetList.clear();
                poetList.addAll(poetPojo.getPoets());
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<PoetPojo> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public Fragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onRefresh() {
        getPoets();
    }
}
