package net.ganjoor.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.ganjoor.R;
import net.ganjoor.model.Poem;
import net.ganjoor.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PoemAdapter extends RecyclerView.Adapter<PoemAdapter.MyViewHolder> {
    private Context mContext;
    private List<Poem> poemList;

    public PoemAdapter(Context mContext, List<Poem> poemList) {
        this.mContext = mContext;
        this.poemList = poemList;
    }

    @Override
    public PoemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poem_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PoemAdapter.MyViewHolder holder, int position) {
        Poem poem = poemList.get(position);
        holder.title.setText(poem.getTitle());
        holder.thumbnail.setBackgroundColor(AppUtils.getRandomMaterialColor("500", mContext.getResources(), mContext.getPackageName()));


    }

    @Override
    public int getItemCount() {
        return poemList.size();
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
