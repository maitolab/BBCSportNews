package com.vsee.bbcsportnews.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Tien on 1/26/2018.
 */

public class ArticleObj implements Parcelable {

    private static final SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
    private static final SimpleDateFormat displayedDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.US);

    private SourceObj source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;


    public ArticleObj() {
        source = new SourceObj();
        author = title = description = url = urlToImage = publishedAt = "";
    }

    public void setSource(SourceObj source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public SourceObj getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDisplayTime() {
        try {
            return displayedDateFormat.format(isoDateFormat.parse(getPublishedAt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {
        return "source = [" + source + "]"
                + ",author = " + author
                + ",title = " + title
                + ",description = " + description
                + ",url = " + url
                + ",urlToImage = " + urlToImage
                + ",publishedAt = " + publishedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArticleObj) {
            ArticleObj comparedArticle = (ArticleObj) obj;
            return this.source.equals(comparedArticle.getSource())
                    && this.author.equals(comparedArticle.getAuthor())
                    && this.title.equals(comparedArticle.getTitle())
                    && this.description.equals(comparedArticle.getDescription())
                    && this.url.equals(comparedArticle.getUrl())
                    && this.urlToImage.equals(comparedArticle.getUrlToImage())
                    && this.publishedAt.equals(comparedArticle.getPublishedAt());
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(source.getId());
        out.writeString(source.getName());
        out.writeString(author);
        out.writeString(title);
        out.writeString(description);
        out.writeString(url);
        out.writeString(urlToImage);
        out.writeString(publishedAt);
    }

    private ArticleObj(Parcel in) {
        this.source = new SourceObj();
        this.source.setId(in.readString());
        this.source.setName(in.readString());
        this.author = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.urlToImage = in.readString();
        this.publishedAt = in.readString();
    }

    public static final Parcelable.Creator<ArticleObj> CREATOR
            = new Parcelable.Creator<ArticleObj>() {
        public ArticleObj createFromParcel(Parcel in) {
            return new ArticleObj(in);
        }

        public ArticleObj[] newArray(int size) {
            return new ArticleObj[size];
        }
    };

}
