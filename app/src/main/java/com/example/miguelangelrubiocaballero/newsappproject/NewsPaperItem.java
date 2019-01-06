package com.example.miguelangelrubiocaballero.newsappproject;

public class NewsPaperItem {

    private String mName;
    private String mDescription;
    private String mUrl;

    public NewsPaperItem(String mName, String mDescription, String mUrl) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mUrl = mUrl;
    }


    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }
}
