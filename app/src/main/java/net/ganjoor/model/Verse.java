package net.ganjoor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("poemId")
    @Expose
    private String poemId;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("text")
    @Expose
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
