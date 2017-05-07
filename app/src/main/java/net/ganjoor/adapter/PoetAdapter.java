package net.ganjoor.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.ganjoor.R;
import net.ganjoor.model.Poet;
import net.ganjoor.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PoetAdapter extends RecyclerView.Adapter<PoetAdapter.MyViewHolder> {
    private Context mContext;
    private List<Poet> poetList;

    public PoetAdapter(Context mContext, List<Poet> poetList) {
        this.mContext = mContext;
        this.poetList = poetList;
    }

    @Override
    public PoetAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poet_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PoetAdapter.MyViewHolder holder, int position) {
        Poet poet = poetList.get(position);
        holder.title.setText(poet.getName());
        holder.thumbnail.setBackgroundColor(AppUtils.getRandomMaterialColor("400"));
        // loading poet image using Glide library
        // Glide.with(mContext).load(poet.getThumbnail()).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return poetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
