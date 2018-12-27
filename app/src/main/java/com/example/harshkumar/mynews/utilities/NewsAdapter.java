package com.example.harshkumar.mynews.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshkumar.mynews.data.News;
import com.example.harshkumar.mynews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news){
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) throws NullPointerException {

        View RootView = convertView;
        if(RootView == null){
            RootView = LayoutInflater.from(getContext()).inflate(R.layout.news_card,
                    parent,false);
        }

        News currentNews = getItem(position);

        TextView title = RootView.findViewById(R.id.news_title);
        title.setText(currentNews.getTitle());

        TextView date = RootView.findViewById(R.id.news_date);
        date.setText(currentNews.getDate());

        TextView tag = RootView.findViewById(R.id.news_tag);
        tag.setText(currentNews.getSource());

        ImageView img = RootView.findViewById(R.id.news_image);

        Picasso.get().load(currentNews.getImageUrl()).resize(80,80).centerCrop().into(img);

        return RootView;
    }
}
