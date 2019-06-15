package com.newsapp.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

public class NewsArrayAdapter extends ArrayAdapter<NewsModel> {
    private final Context context;
    private final NewsModel[] values;

    public NewsArrayAdapter(Context context, NewsModel[] values) {
        super(context, R.layout.news_layout, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.news_layout, parent, false);
        ImageView image = (ImageView) cardView.findViewById(R.id.image);
        TextView title = (TextView) cardView.findViewById(R.id.title);
        TextView details = (TextView) cardView.findViewById(R.id.details);
        getImage(values[position].img, image);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        title.setText(values[position].title);
        details.setText(values[position].details);

        return cardView;
    }

    private void getImage(String URL, ImageView imageView) {
        Ion.with(imageView)
                .error(R.drawable.no_image)
                .load(URL);
    }
}
