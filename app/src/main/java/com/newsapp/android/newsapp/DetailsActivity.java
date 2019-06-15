package com.newsapp.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

public class DetailsActivity extends AppCompatActivity {
    NewsModel newsModel;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mydb = new DBHelper(this);

        newsModel = (NewsModel) getIntent().getSerializableExtra("NewsModel");
        ImageView image = findViewById(R.id.image);
        TextView title = findViewById(R.id.title);
        TextView details = findViewById(R.id.details);
        TextView content = findViewById(R.id.content);

        Ion.with(image)
                .error(R.drawable.no_image)
                .load(newsModel.img);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(newsModel.url));
                startActivity(viewIntent);
            }
        });

        title.setText(newsModel.title);
        details.setText(newsModel.details);
        content.setText(newsModel.content);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.insertNews(newsModel.img, newsModel.title, newsModel.publishedAt, newsModel.author, newsModel.content, newsModel.url, newsModel.source);
                Snackbar.make(view, "News saved!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
