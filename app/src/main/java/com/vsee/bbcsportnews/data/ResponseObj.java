package com.vsee.bbcsportnews.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tien on 1/26/2018.
 */

public class ResponseObj {
    private String status;
    private int totalResults;
    private ArrayList<ArticleObj> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<ArticleObj> getArticles() {
        return articles;
    }

    @Override
    public String toString() {

        return "status = " + status
                + ",totalResults = " + totalResults
                + ",articles = " + Arrays.toString(articles.toArray());
    }

}
