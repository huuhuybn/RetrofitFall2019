package com.dotplays.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    public static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://113.190.232.235:30100")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
