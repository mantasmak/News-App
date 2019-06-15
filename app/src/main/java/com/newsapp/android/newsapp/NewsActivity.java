package com.newsapp.android.newsapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends ListActivity {
    public ArrayList<NewsModel> values = new ArrayList<NewsModel>();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        Ion.with(this)
                .load(getIntent().getStringExtra("url"))
                .asString()
                .setCallback(new FutureCallback<String>() {
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);
                            JSONArray a = json.getJSONArray("articles");
                            for(int i = 0; i < a.length(); i++) {
                                JSONObject article = a.getJSONObject(i);
                                JSONObject source = article.getJSONObject("source");
                                values.add(new NewsModel(article.getString("urlToImage"), article.getString("title"), article.getString("publishedAt"),
                                        article.getString("author"), article.getString("content"), article.getString("url"), source.getString("name")));
                            }
                            NewsArrayAdapter adapter = new NewsArrayAdapter(context, values.toArray(new NewsModel[values.size()]));
                            setListAdapter(adapter);
                        } catch (JSONException jsone) {
                            Log.wtf("help", jsone);
                        }
                    }
                });

        SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("NewsModel", values.get(position));
        startActivity(intent);
    }
}

