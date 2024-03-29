package com.rifqit.footballlab;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCilentInstance {

    private static Retrofit retrofit;
    private static final  String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit  = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
