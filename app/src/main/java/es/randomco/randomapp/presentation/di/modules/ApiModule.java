package es.randomco.randomapp.presentation.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.randomco.randomapp.data.datasource.network.ApiService;
import es.randomco.randomapp.data.datasource.network.NetworkDataSourceImpl;
import es.randomco.randomapp.data.datasource.network.interceptor.ErrorInterceptor;
import es.randomco.randomapp.domain.repository.datasource.NetworkDataSource;
import es.randomco.randomapp.presentation.di.qualifiers.Endpoint;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module()
public class ApiModule {

    @Provides
    @Singleton
    public NetworkDataSource provideNetworkDataSource(NetworkDataSourceImpl networkDataSource) {
        return networkDataSource;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(@Endpoint String enpoint, GsonConverterFactory gsonConverterFactory,
                                        OkHttpClient okClient) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(enpoint)
                .client(okClient)
                .addConverterFactory(gsonConverterFactory)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient(ErrorInterceptor errorInterceptor,
                                      HttpLoggingInterceptor loggingInterceptor) {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(errorInterceptor)
                .addInterceptor(loggingInterceptor)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        return okHttpBuilder.build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Gson provideGsonParser() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @Singleton
    @Endpoint
    public String provideEndpoint() {
        return ApiService.BASE_URL;
    }
}
