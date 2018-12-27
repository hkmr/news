package com.example.harshkumar.mynews.utilities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.harshkumar.mynews.data.NewsSource;

import java.util.List;

public class SourceLoader extends AsyncTaskLoader<List<NewsSource>> {

    private String url;

    public SourceLoader(Context context,String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<NewsSource> loadInBackground() {

        if(url == null)
            return null;

        List<NewsSource> sources = QueryUtilsForSource.fetchData(url);
        return sources;

    }
}
