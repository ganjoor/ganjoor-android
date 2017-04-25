package net.ganjoor.service;


import net.ganjoor.utils.AppUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(AppUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
