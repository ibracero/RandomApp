package es.randomco.randomapp.data.datasource.network;


import java.util.List;

import es.randomco.randomapp.data.datasource.network.models.ApiUser;
import es.randomco.randomapp.data.datasource.network.response.ApiUserResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    String BASE_URL = "https://api.randomuser.me";

    @GET("/?results=40")
    Call<ApiUserResponse> getUsers();
}
