package com.newsapp.android.newsapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SavedNewsActivity extends ListActivity {
    public ArrayList<NewsModel> values = new ArrayList<NewsModel>();
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mydb = new DBHelper(this);
        values = mydb.getAllNews();
        Collections.reverse(values);
        NewsArrayAdapter adapter = new NewsArrayAdapter(this, values.toArray(new NewsModel[values.size()]));
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("NewsModel", values.get(position));
        startActivity(intent);
    }
}
