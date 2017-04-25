package net.ganjoor.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PoetPojo {
    @SerializedName("poets")
    @Expose
    private List<Poet> poets = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Poet> getPoets() {
        return poets;
    }

    public void setPoets(List<Poet> poets) {
        this.poets = poets;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
