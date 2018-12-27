package com.example.harshkumar.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.harshkumar.mynews.data.NewsSource;
import com.example.harshkumar.mynews.utilities.NewsSourceAdapter;
import com.example.harshkumar.mynews.utilities.SourceLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsSourceActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsSource>>  {

    private List<NewsSource> newsSourceList;
    private RecyclerView recyclerView;
    private NewsSourceAdapter adapter;
    OkHttpClient client = new OkHttpClient();

    private static final String LOG_TAG = NewsSourceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_source);

        recyclerView = (RecyclerView) findViewById(R.id.news_source_view);

        adapter = new NewsSourceAdapter(newsSourceList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }


    @NonNull
    @Override
    public Loader<List<NewsSource>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new SourceLoader(this,buildUrl());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsSource>> loader, List<NewsSource> sourceList) {
        loader.startLoading();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsSource>> loader) {

    }

    /*
     * Create Url Based on settings*/
    private String buildUrl(){

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https").authority("newsapi.org");
        uriBuilder.appendPath("v2").appendPath("sources");
        uriBuilder.appendQueryParameter("apiKey","8d632abfccc14f5da6c09f95f2fafd42");

        Log.i(LOG_TAG,uriBuilder.toString());

        return uriBuilder.toString();
    }



}
