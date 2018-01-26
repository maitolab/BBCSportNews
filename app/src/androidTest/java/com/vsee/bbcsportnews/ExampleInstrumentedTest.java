package com.vsee.bbcsportnews;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.vsee.bbcsportnews.data.ArticleObj;
import com.vsee.bbcsportnews.service.DatabaseManager;
import com.vsee.bbcsportnews.data.SourceObj;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final int MAX_TEST_STRING_LENGTH = 200;

    @Test
    public void testSaveStoreHeartShape() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseManager databaseManager = new DatabaseManager(appContext);
        Random random = new Random();
        int testedArticleNumber = 1 + random.nextInt(1000);
        ArrayList<ArticleObj> articles = new ArrayList<>(testedArticleNumber);
        for (int i = 0; i < testedArticleNumber; i++) {
            ArticleObj articleObj = new ArticleObj();
            articleObj.setSource(new SourceObj());
            articleObj.setAuthor(new RandomString(1 + random.nextInt(MAX_TEST_STRING_LENGTH)).nextString());
            articleObj.setTitle(new RandomString(1 + random.nextInt(MAX_TEST_STRING_LENGTH)).nextString());
            articleObj.setDescription(new RandomString(1 + random.nextInt(MAX_TEST_STRING_LENGTH)).nextString());
            articleObj.setUrl(new RandomString(1 + random.nextInt(MAX_TEST_STRING_LENGTH)).nextString());
            articleObj.setUrlToImage(new RandomString(1 + random.nextInt(MAX_TEST_STRING_LENGTH)).nextString());
            articleObj.setPublishedAt(new RandomString(1 + random.nextInt(MAX_TEST_STRING_LENGTH)).nextString());
            articles.add(articleObj);
        }
        databaseManager.saveArticles(articles);
        ArrayList<ArticleObj> storedArticles = databaseManager.storeArticles();
        assertEquals(storedArticles.size(), articles.size());

        for (int i = 0; i < testedArticleNumber; i++) {
            assertEquals(storedArticles.get(i), articles.get(i));
        }
    }

}
