package net.ganjoor.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poem {
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
}
