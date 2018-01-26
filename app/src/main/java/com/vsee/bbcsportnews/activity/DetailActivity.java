package com.vsee.bbcsportnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import com.vsee.bbcsportnews.R;
import com.vsee.bbcsportnews.data.ArticleObj;

public class DetailActivity extends AppCompatActivity {
    public static final String ARTICLE_KEY = "article_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ArticleObj article = getIntent().getParcelableExtra(ARTICLE_KEY);

        if (article != null) {
            TextView title = findViewById(R.id.txt_title);
            title.setText(article.getTitle());
            TextView description = findViewById(R.id.txt_des);
            description.setText(article.getDescription());
            TextView author = findViewById(R.id.txt_author);
            author.setText(article.getAuthor());
            TextView time = findViewById(R.id.txt_time);
            time.setText(article.getDisplayTime());
            WebView webView = findViewById(R.id.wv_detail);
            webView.loadUrl(article.getUrl());
        }

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
