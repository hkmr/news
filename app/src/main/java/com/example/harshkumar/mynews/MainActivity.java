package com.example.harshkumar.mynews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.harshkumar.mynews.data.News;
import com.example.harshkumar.mynews.utilities.NewsAdapter;
import com.example.harshkumar.mynews.utilities.NewsLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int LOADER_ID = 1;
    private NewsAdapter mNewsAdapter;
    TextView emptyView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id =item.getItemId();
        if(id == R.id.setting_menu_item) {
            Intent settingIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = findViewById(R.id.news_list_view);
        mNewsAdapter = new NewsAdapter(this,new ArrayList<News>());
        newsListView.setAdapter(mNewsAdapter);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        emptyView = findViewById(R.id.empty_text_view);

        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(LOADER_ID,null,this);
        }else{
            View progressBar = findViewById(R.id.progess_bar);
            progressBar.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText(R.string.no_internet_connection);
        }

        newsListView.setEmptyView(emptyView);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                News currentNews = mNewsAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this,NewsDetailActivity.class);
//                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(currentNews.getUrl()));
                startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(this, buildUrl());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {

        View progressBar = findViewById(R.id.progess_bar);
        progressBar.setVisibility(View.GONE);

        emptyView.setText(R.string.no_data);

        if(news != null && !news.isEmpty()){
            mNewsAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }

    private String buildUrl(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String source = sharedPreferences.getString(
                getString(R.string.setting_news_source_key),
                getString(R.string.setting_news_source_default));

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https").authority("newsapi.org");
        uriBuilder.appendPath("v2").appendPath("top-headlines");
        uriBuilder.appendQueryParameter("sources",source);
        uriBuilder.appendQueryParameter("apiKey","8d632abfccc14f5da6c09f95f2fafd42");

        Log.i(LOG_TAG,uriBuilder.toString());

        return uriBuilder.toString();
    }

}
