package es.randomco.randomapp.data.datasource.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiProfilePicture {

    @SerializedName("large")
    @Expose
    String large;

    @SerializedName("medium")
    @Expose
    String medium;

    @SerializedName("thumbnail")
    @Expose
    String thumbnail;

    public ApiProfilePicture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
