package com.example.miguelangelrubiocaballero.newsappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import static com.example.miguelangelrubiocaballero.newsappproject.NewsPaper.EXTRA_URL;

public class NewsPaperDetailActivity extends AppCompatActivity {

    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_paper_detail);


        Intent intent = getIntent();

        String url = intent.getStringExtra(EXTRA_URL);

        WebView webView = findViewById(R.id.webView_newspaper);
        loader = (ProgressBar) findViewById(R.id.loader_newspaper);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    loader.setVisibility(View.GONE);
                } else {
                    loader.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
