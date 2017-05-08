package net.ganjoor.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;

import net.ganjoor.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerseCombine extends AbstractItem<VerseCombine, VerseCombine.MyViewHolder> {

    private String id;

    private String poemId;

    private String position;

    private String order;

    private String textRight;

    private String textLeft;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoemId() {
        return poemId;
    }

    public void setPoemId(String poemId) {
        this.poemId = poemId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTextRight() {
        return textRight;
    }

    public void setTextRight(String textRight) {
        this.textRight = textRight;
    }

    public String getTextLeft() {
        return textLeft;
    }

    public void setTextLeft(String textLeft) {
        this.textLeft = textLeft;
    }

    @Override
    public MyViewHolder getViewHolder(View v) {
        return new MyViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.fastadapter_sampleitem_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.verse_card;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(MyViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);
        viewHolder.checkBox.setChecked(isSelected());
        viewHolder.verseTextRight.setText(textRight);
        viewHolder.verseTextLeft.setText(textLeft);
    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(MyViewHolder holder) {
        super.unbindView(holder);
        holder.verseTextRight.setText(null);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.verse_text_right)
        TextView verseTextRight;
        @BindView(R.id.verse_text_left)
        TextView verseTextLeft;
        @BindView(R.id.checkbox)
        CheckBox checkBox;

        private MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class CheckBoxClickEvent extends ClickEventHook<VerseCombine> {
        @Override
        public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof VerseCombine.MyViewHolder) {
                return ((VerseCombine.MyViewHolder) viewHolder).checkBox;
            }
            return null;
        }

        @Override
        public void onClick(View v, int position, FastAdapter<VerseCombine> fastAdapter, VerseCombine item) {
            fastAdapter.toggleSelection(position);
        }
    }
}
