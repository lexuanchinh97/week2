package com.example.lexuanchinh.newsarticlesearch.util;

import com.example.lexuanchinh.newsarticlesearch.API.APIInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    public static String API_KEY = "180691ccec03448aa283e97b627c3e00";//227c750bb7714fc39ef1559ef1bd8329
    public static String BASE_URL = "https://api.nytimes.com";
   // public static String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
    //http://image.tmdb.org/t/p/w185/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg
    private static APIInterface instance = null;
    public static APIInterface getInstance() {
        if (instance == null) {
            synchronized (APIService.class) {
                if (instance == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.writeTimeout(15 * 60 * 1000, TimeUnit.MILLISECONDS);
                    httpClient.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
                    httpClient.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS);

                    OkHttpClient client = httpClient.build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                    instance = retrofit.create(APIInterface.class);
                }
            }
        }
        return  instance;
    }
}
