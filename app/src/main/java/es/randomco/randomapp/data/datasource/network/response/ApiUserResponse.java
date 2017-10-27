package es.randomco.randomapp.data.datasource.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import es.randomco.randomapp.data.datasource.network.models.ApiUser;

public class ApiUserResponse {

    @SerializedName("results")
    @Expose
    List<ApiUser> results;

    public List<ApiUser> getResults() {
        return results;
    }
}
