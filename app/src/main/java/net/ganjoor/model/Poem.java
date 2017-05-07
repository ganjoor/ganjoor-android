package net.ganjoor.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mikepenz.fastadapter.items.AbstractItem;

import net.ganjoor.R;
import net.ganjoor.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Poem extends AbstractItem<Poem, Poem.MyViewHolder> implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.categoryId);
        dest.writeString(this.url);
        dest.writeString(this.title);
    }

    public Poem() {
    }

    protected Poem(Parcel in) {
        this.id = in.readString();
        this.categoryId = in.readString();
        this.url = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Poem> CREATOR = new Parcelable.Creator<Poem>() {
        @Override
        public Poem createFromParcel(Parcel source) {
            return new Poem(source);
        }

        @Override
        public Poem[] newArray(int size) {
            return new Poem[size];
        }
    };
    @Override
    public int getType() {
        return R.id.fastadapter_sampleitem_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.poem_card;
    }
    @Override
    public void bindView(Poem.MyViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);
        viewHolder.title.setText(title);
        viewHolder.thumbnail.setBackgroundColor(AppUtils.getRandomMaterialColor("500"));

    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(Poem.MyViewHolder holder) {
        super.unbindView(holder);

    }
    @Override
    public MyViewHolder getViewHolder(View v) {
        return new Poem.MyViewHolder(v);
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
