package net.ganjoor.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PoemPojo {

    @SerializedName("poems")
    @Expose
    private List<Poem> poems = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Poem> getPoems() {
        return poems;
    }

    public void setPoems(List<Poem> poems) {
        this.poems = poems;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
