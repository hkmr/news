package com.example.harshkumar.mynews;

import android.app.NotificationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        contentTextView.append("\n"+news.getUrl());

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

    public void sendNotification(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.share_icon);
        builder.setContentTitle("First Notification");
        builder.setContentText("Hola, this is my first notifation from StackNews");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

    }
}
