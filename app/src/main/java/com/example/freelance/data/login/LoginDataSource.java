package com.example.freelance.data.login;

import android.content.Context;
import android.widget.Toast;

import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.app.Const;
import com.example.freelance.data.Result;
import com.example.freelance.data.model.LoggedInUser;
import com.example.freelance.data.model.Login;
import com.example.freelance.network.ApiService;
import com.example.freelance.utils.PrefUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    ApiService apiService;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient;

    public LoginDataSource(){
        initOkHttp(App.getContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL_TOKEN)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void login(final String username, final String password, final LoginRepositoryCallback callback) {

        try {
            Call<com.example.freelance.data.model.Result<Login>> tokenCall = apiService.login(username, password);
            tokenCall.enqueue(new Callback<com.example.freelance.data.model.Result<Login>>() {
                @Override
                public void onResponse(Call<com.example.freelance.data.model.Result<Login>> call, Response<com.example.freelance.data.model.Result<Login>> response) {
                    if (response.body()!=null && response.body().isSuccess()){
                        com.example.freelance.data.model.Result<Login> token = response.body();
                        PrefUtils.getInstance().storeKeys(App.getContext(), App.getContext().getString(R.string.token),token.getData().getMeta().getToken());
                        LoggedInUser user = new LoggedInUser(password, username);
                        callback.isLogin(new Result.Success<>(user));
                    }else {
                        callback.isLogin(new Result.Error(new IOException("Error logging in")));
                    }
                }

                @Override
                public void onFailure(Call<com.example.freelance.data.model.Result<Login>> call, Throwable t) {
                    callback.isLogin(new Result.Error(new IOException("Error logging in", t)));
                }
            });
        } catch (Exception e) {
            callback.isLogin(new Result.Error(new IOException("Error logging in", e)));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private static void initOkHttp(final Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder;
                requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        okHttpClient = httpClient.build();
    }
}
