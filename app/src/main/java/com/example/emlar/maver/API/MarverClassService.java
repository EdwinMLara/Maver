package com.example.emlar.maver.API;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarverClassService {

    public static MarvelService getMarvelApi(){

        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    HttpUrl originalUrl = originalRequest.url();
                    HttpUrl httpUrl = originalUrl.newBuilder()
                            .addQueryParameter(MarvelService.API_KEY_KEY,MarvelService.API_KEY_VALUE)
                            .addQueryParameter(MarvelService.TIME_STAMP_KEY,MarvelService.TIME_STAMP_VALUE)
                            .addQueryParameter(MarvelService.HASH_KEY,MarvelService.HASH_VALUE)
                            .build();

                    Request.Builder requestBuilder = originalRequest.newBuilder().url(httpUrl);
                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                }
            }).build();


        return new Retrofit.Builder()
                .baseUrl(MarvelService.BASE_URl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelService.class);
    }
}
