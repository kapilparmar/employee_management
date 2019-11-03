package com.kapil.employeemanagement.retrofit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpServerApiClient {

    private static final String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private static Retrofit retrofitApiClient;

    public static Retrofit getClient(){

        if(retrofitApiClient == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

           retrofitApiClient = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create(gson))
                   .client(client)
                   .build();
        }

        return retrofitApiClient;
    }

}
