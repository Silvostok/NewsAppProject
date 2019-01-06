package com.example.miguelangelrubiocaballero.newsappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsPaper extends AppCompatActivity implements NewsPaperAdapter.OnItemClickListener{


    public static final String EXTRA_URL = "creatorUrl";

    private RecyclerView mRecyclerView;
    private NewsPaperAdapter mNewsPaperAdapter;
    private ArrayList<NewsPaperItem> mNwesPaperList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news_paper);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNwesPaperList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


    }


    private void parseJSON() {

        String url = "https://newsapi.org/v2/sources?apiKey=2e377f97e1f043d298b36e09314d9dfc";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("sources");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject source = jsonArray.getJSONObject(i);

                                String creatorName = source.getString("name");
                                String creatorDescription = source.getString("description");
                                String creatorUrl = source.getString("url");


                                mNwesPaperList.add(new NewsPaperItem(creatorName,creatorDescription,creatorUrl));
                            }

                            mNewsPaperAdapter = new NewsPaperAdapter(NewsPaper.this, mNwesPaperList);
                            mRecyclerView.setAdapter(mNewsPaperAdapter);

                            mNewsPaperAdapter.setOnItemClickListener(NewsPaper.this);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, NewsPaperDetailActivity.class);
        NewsPaperItem clickedItem = mNwesPaperList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getmUrl());
        startActivity(detailIntent);

    }
}
