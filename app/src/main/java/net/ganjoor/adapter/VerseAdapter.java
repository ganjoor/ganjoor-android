package net.ganjoor.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.ganjoor.R;
import net.ganjoor.model.Verse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerseAdapter extends RecyclerView.Adapter<VerseAdapter.MyViewHolder> {
    private Context mContext;
    private List<Verse> verseList;

    public VerseAdapter(Context mContext, List<Verse> verseList) {
        this.mContext = mContext;
        this.verseList = verseList;
    }

    @Override
    public VerseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.verse_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VerseAdapter.MyViewHolder holder, int position) {
        Verse verse = verseList.get(position);
        holder.verse_text.setText(verse.getText());
    }

    @Override
    public int getItemCount() {
        return verseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.verse_text)
        TextView verse_text;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
