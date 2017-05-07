package net.ganjoor.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
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

public class Poet extends AbstractItem<Poet, Poet.MyViewHolder> implements Parcelable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("description")
    @Expose
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.categoryId);
        dest.writeString(this.description);
    }

    public Poet() {
    }

    protected Poet(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.categoryId = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Poet> CREATOR = new Parcelable.Creator<Poet>() {
        @Override
        public Poet createFromParcel(Parcel source) {
            return new Poet(source);
        }

        @Override
        public Poet[] newArray(int size) {
            return new Poet[size];
        }
    };
    @Override
    public Poet.MyViewHolder getViewHolder(View v) {
        return new Poet.MyViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.fastadapter_sampleitem_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.poet_card;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(Poet.MyViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);
        viewHolder.title.setText(name);
        viewHolder.thumbnail.setBackgroundColor(AppUtils.getRandomMaterialColor("400"));

    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(Poet.MyViewHolder holder) {
        super.unbindView(holder);

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
