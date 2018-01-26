package com.vsee.bbcsportnews.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vsee.bbcsportnews.data.ArticleObj;

import java.util.ArrayList;

/**
 * Created by Tien on 1/26/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "news.db";
    private static final String ARTICLE_TABLE_NAME = "article";
    private static final String ARTICLE_COLUMN_ID = "id";
    private static final String ARTICLE_COLUMN_AUTHOR = "author";
    private static final String ARTICLE_COLUMN_TITLE = "title";
    private static final String ARTICLE_COLUMN_DESCRIPTION = "description";
    private static final String ARTICLE_COLUMN_URL = "url";
    private static final String ARTICLE_COLUMN_URL_TO_IMAGE = "url_to_image";
    private static final String ARTICLE_COLUMN_TIME = "published_at";

    private static final int VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists " + ARTICLE_TABLE_NAME
                + " (" + ARTICLE_COLUMN_ID + " integer primary key autoincrement,"
                + ARTICLE_COLUMN_AUTHOR + " text,"
                + ARTICLE_COLUMN_TITLE + " text,"
                + ARTICLE_COLUMN_DESCRIPTION + " text,"
                + ARTICLE_COLUMN_URL + " text,"
                + ARTICLE_COLUMN_URL_TO_IMAGE + " text,"
                + ARTICLE_COLUMN_TIME + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ARTICLE_TABLE_NAME);
        onCreate(db);
    }

    public void saveArticles(ArrayList<ArticleObj> articles) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ARTICLE_TABLE_NAME, null, null);
        for (ArticleObj article : articles) {
            ContentValues articleContent = new ContentValues();
            articleContent.put(ARTICLE_COLUMN_AUTHOR, article.getAuthor());
            articleContent.put(ARTICLE_COLUMN_TITLE, article.getTitle());
            articleContent.put(ARTICLE_COLUMN_DESCRIPTION, article.getDescription());
            articleContent.put(ARTICLE_COLUMN_URL, article.getUrl());
            articleContent.put(ARTICLE_COLUMN_URL_TO_IMAGE, article.getUrlToImage());
            articleContent.put(ARTICLE_COLUMN_TIME, article.getPublishedAt());
            db.insert(ARTICLE_TABLE_NAME, null, articleContent);
        }
    }

    public ArrayList<ArticleObj> storeArticles() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + ARTICLE_TABLE_NAME, null);
        res.moveToFirst();
        ArrayList<ArticleObj> returnArticles = new ArrayList<>();
        while (!res.isAfterLast()) {
            ArticleObj article = new ArticleObj();
            article.setAuthor(res.getString(res.getColumnIndex(ARTICLE_COLUMN_AUTHOR)));
            article.setTitle(res.getString(res.getColumnIndex(ARTICLE_COLUMN_TITLE)));
            article.setDescription(res.getString(res.getColumnIndex(ARTICLE_COLUMN_DESCRIPTION)));
            article.setUrl(res.getString(res.getColumnIndex(ARTICLE_COLUMN_URL)));
            article.setUrlToImage(res.getString(res.getColumnIndex(ARTICLE_COLUMN_URL_TO_IMAGE)));
            article.setPublishedAt(res.getString(res.getColumnIndex(ARTICLE_COLUMN_TIME)));
            returnArticles.add(article);
            res.moveToNext();
        }
        return returnArticles;
    }

}