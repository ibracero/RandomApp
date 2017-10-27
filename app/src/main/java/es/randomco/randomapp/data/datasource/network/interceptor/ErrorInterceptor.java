package es.randomco.randomapp.data.datasource.network.interceptor;

import android.app.Application;

import java.io.IOException;

import javax.inject.Inject;

import es.randomco.randomapp.R;
import es.randomco.randomapp.domain.exceptions.NetworkException;
import es.randomco.randomapp.utils.Utils;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {

    private final Application mContext;

    @Inject
    public ErrorInterceptor(Application context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (!Utils.isOnline(mContext)) {
            throw new NetworkException("OFFLINE", mContext.getString(R.string.network_error));
        }

        Response response = chain.proceed(chain.request());
        if (!response.isSuccessful()) {
            throw new NetworkException(String.valueOf(response.code()), mContext.getString(R.string.generic_api_error));
        }

        return response;
    }
}
