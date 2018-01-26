package com.vsee.bbcsportnews.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vsee.bbcsportnews.R;
import com.vsee.bbcsportnews.adapter.ArticleAdapter;
import com.vsee.bbcsportnews.data.ArticleObj;
import com.vsee.bbcsportnews.data.ResponseObj;
import com.vsee.bbcsportnews.service.DatabaseManager;
import com.vsee.bbcsportnews.service.RestAPIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Call<ResponseObj> getArticleRequest;
    private DatabaseManager databaseManager;
    private ArrayList<ArticleObj> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intSwipeRefresh();
        initArticleList();
    }

    private void initArticleList() {
        databaseManager = new DatabaseManager(this);
        recyclerView = findViewById(R.id.list_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getArticlesFromDatabase();
        adapter = new ArticleAdapter(articles, MainActivity.this);
        recyclerView.setAdapter(adapter);
        // there is no cache data
        if (articles == null || articles.size() == 0) {
            swipeRefreshLayout.setRefreshing(true);
            getArticleFromServer();
        }
    }

    private void intSwipeRefresh() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this::getArticleFromServer);
    }

    private void getArticlesFromDatabase() {
        articles = databaseManager.storeArticles();
    }

    private void getArticleFromServer() {

        if (getArticleRequest != null) {
            getArticleRequest.cancel();
        }

        getArticleRequest = RestAPIService.getInstance().getTopHeadLinesNews();
        getArticleRequest.enqueue(new Callback<ResponseObj>() {
            @Override
            public void onResponse(@NonNull Call<ResponseObj> call, @NonNull Response<ResponseObj> response) {
                ResponseObj responseObj = response.body();
                Log.i(LOG_TAG, "onResponse = " + responseObj);
                swipeRefreshLayout.setRefreshing(false);

                if (responseObj == null) {
                    return;
                }

                articles = responseObj.getArticles();
                adapter.setData(articles);
                databaseManager.saveArticles(articles);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseObj> call, @NonNull Throwable t) {
                Log.i(LOG_TAG, "onFailure = " + t);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
