package com.vsee.bbcsportnews.service;

import com.vsee.bbcsportnews.data.ResponseObj;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tien on 1/26/2018.
 */

public class RestAPIService {

    private static final String SERVER_URL = "https://newsapi.org/";
    private static final String API_KEY = "af19337c4e7144d39ecb44b2465476de";
    private static final String SOURCE_ID = "bbc-sport";

    private static RestAPIService instance;
    private RestAPI service;

    private RestAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RestAPI.class);

    }

    public static RestAPIService getInstance() {
        if (instance == null)
            instance = new RestAPIService();
        return instance;
    }

    public Call<ResponseObj> getTopHeadLinesNews() {
        return service.getTopHeadLinesNews(SOURCE_ID, API_KEY);
    }

}
