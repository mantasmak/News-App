package com.newsapp.android.newsapp;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DBHelper mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button wnbutt = findViewById(R.id.wnbutton);
        Button lnbutt = findViewById(R.id.lnbutton);
        Button tbutt = findViewById(R.id.tbutton);
        Button snbutt = findViewById(R.id.snbutton);
        Button csbutt = findViewById(R.id.csbutton);


        wnbutt.getBackground().setColorFilter(new LightingColorFilter(0x7C4DFF, 0x0));
        lnbutt.getBackground().setColorFilter(new LightingColorFilter(0x7C4DFF, 0x0));
        tbutt.getBackground().setColorFilter(new LightingColorFilter(0x7C4DFF, 0x0));
        snbutt.getBackground().setColorFilter(new LightingColorFilter(0x7C4DFF, 0x0));
        csbutt.getBackground().setColorFilter(new LightingColorFilter(0x7C4DFF, 0x0));

    }

    public void buttonClick(View view) {
        Intent intent = new Intent(this, NewsActivity.class);

        if (view.getId() == R.id.wnbutton) {
            intent.putExtra("url", "https://newsapi.org/v2/top-headlines?country=us&apiKey=574495176c8346cb8509bcb8cd720777");
            startActivity(intent);
        } else if (view.getId() == R.id.lnbutton) {
            intent.putExtra("url", "https://newsapi.org/v2/top-headlines?country=lt&apiKey=574495176c8346cb8509bcb8cd720777");
            startActivity(intent);
        } else if (view.getId() == R.id.tbutton) {
            intent.putExtra("url", "https://newsapi.org/v2/top-headlines?category=technology&country=us&apiKey=574495176c8346cb8509bcb8cd720777");
            startActivity(intent);
        } else if (view.getId() == R.id.snbutton) {
            Intent savedNewsIntent = new Intent(this, SavedNewsActivity.class);
            startActivity(savedNewsIntent);
        } else if (view.getId() == R.id.csbutton) {
            Intent customSearchIntent = new Intent(this, CustomSearchActivity.class);
            startActivity(customSearchIntent);
        }
    }
}
