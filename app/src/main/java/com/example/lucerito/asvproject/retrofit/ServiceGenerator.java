package com.example.lucerito.asvproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder createRequest(final String url) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        return builder;
    }

    public static <T> T createService(Class<T> serviceClass, String url) {
        Retrofit adapter = createRequest(url).build();
        return adapter.create(serviceClass);
    }
}
