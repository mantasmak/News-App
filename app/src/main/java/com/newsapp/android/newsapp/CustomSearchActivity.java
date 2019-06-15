package com.newsapp.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.HashMap;

public class CustomSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String countrySelected;
    String categorySelected;
    HashMap<String, String> countryMap;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search);

        Spinner countrySpinner = findViewById(R.id.country);
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner .setAdapter(countryAdapter);

        Spinner categorySpinner = findViewById(R.id.category);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner .setAdapter(categoryAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (view.getId() == R.id.country){
            countrySelected = (String) parent.getItemAtPosition(pos);
        } else if(view.getId() == R.id.category)
            categorySelected = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private String setUrl() {
        //https://newsapi.org/v2/sources?category=health&country=us&apiKey=574495176c8346cb8509bcb8cd720777
        return "https://newsapi.org/v2/top-headlines?category=" + categorySelected.toLowerCase() + "&country=" + countryMap.get(countrySelected) + "&apiKey=574495176c8346cb8509bcb8cd720777";
    }

    private void loadMap() {
        countryMap = new HashMap<String, String>();
        String[] countries = getResources().getStringArray(R.array.countries_array);
        String[] codes = getResources().getStringArray(R.array.code_array);

        for(int i = 0; i < countries.length; i++){
            countryMap.put(countries[i], codes[i]);
        }
    }

    public void buttonClick(View view) {
        Spinner country = findViewById(R.id.country);
        Spinner category = findViewById(R.id.category);
        countrySelected = country.getSelectedItem().toString();
        categorySelected = category.getSelectedItem().toString();
        loadMap();
        Log.i("wwwwwwwwwwwwwwwwwwwwww", setUrl());
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("url", setUrl());
        startActivity(intent);
    }
}
