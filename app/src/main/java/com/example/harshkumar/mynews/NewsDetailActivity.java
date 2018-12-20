package com.example.harshkumar.mynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.harshkumar.mynews.data.News;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Object newsObject = getIntent().getSerializableExtra("newsObj");
        News news = (News) newsObject;

        TextView titleTextView = findViewById(R.id.news_detail_title);
        titleTextView.setText(news.getTitle());

        TextView contentTextView = findViewById(R.id.news_detail_content);
        contentTextView.setText(news.getContent());
        contentTextView.append("\n"+news.getUrl());

        ImageView imageView = findViewById(R.id.news_detail_image);
        Picasso.get().load(news.getImageUrl()).into(imageView);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
