package com.example.harshkumar.mynews.utilities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshkumar.mynews.R;
import com.example.harshkumar.mynews.data.NewsSource;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsSourceAdapter extends RecyclerView.Adapter<NewsSourceAdapter.MyViewHolder> {


    private List<NewsSource> newsSourceList;

    public NewsSourceAdapter(List<NewsSource> sourceList){
        this.newsSourceList = sourceList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_source_card,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        NewsSource source = newsSourceList.get(i);
        myViewHolder.sourceName.setText(source.getmName());
        myViewHolder.sourceCategory.setText(source.getmCategory());
        Picasso.get().load(source.getmImageUrl()).resize(50,50).centerCrop().into(myViewHolder.sourceImage);

    }

    @Override
    public int getItemCount() {
        return newsSourceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView sourceName;
        public TextView sourceCategory;
        public ImageView sourceImage;

        public MyViewHolder(View view){
            super(view);
            this.sourceName = (TextView) view.findViewById(R.id.news_source_name);
            this.sourceCategory = (TextView) view.findViewById(R.id.news_source_category);
            this.sourceImage = (ImageView) view.findViewById(R.id.news_source_image);

        }
    }
}
