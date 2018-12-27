package com.example.harshkumar.mynews;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

        ImageView imageView = findViewById(R.id.news_detail_image);
        Picasso.get().load(news.getImageUrl()).into(imageView);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    public void sendNotification(){

        Intent intent = new Intent(this,NewsSourceActivity.class);
        startActivity(intent);

    }
}
