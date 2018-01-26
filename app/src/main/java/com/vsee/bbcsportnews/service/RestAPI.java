package com.vsee.bbcsportnews.service;

import com.vsee.bbcsportnews.data.ResponseObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tien on 1/26/2018.
 */

public interface RestAPI {
    @GET("v2/top-headlines")
    Call<ResponseObj> getTopHeadLinesNews(@Query("sources") String source, @Query("apiKey") String apiKey);
}
