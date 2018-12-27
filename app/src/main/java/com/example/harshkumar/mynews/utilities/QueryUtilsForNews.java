package com.example.harshkumar.mynews.utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.harshkumar.mynews.data.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtilsForNews {

    private static final String LOG_TAG = QueryUtilsForNews.class.getSimpleName();

    private QueryUtilsForNews(){}

    public static List<News> fetchData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem in making the HTTP request",e);
        }

        List<News> news = extractFeatureFromJson(jsonResponse);
        return news;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error with URL",e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG,"Error Response Code = "+httpURLConnection.getResponseCode());
            }
        }catch(IOException e){
                Log.e(LOG_TAG,"Error in Connection with url",e);
        }finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream)throws IOException{
        StringBuilder output = new StringBuilder();

        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                    Charset.forName("UTF-8"));

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String jsonResponse){
        if(TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        String title,content,date,source,author,url,imageUrl;

        List<News> news = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray articles = jsonObject.getJSONArray("articles");

            for(int i=0; i< articles.length(); i++){

                JSONObject object = articles.getJSONObject(i);
                title = object.getString("title");
                url = object.getString("url");
                author = object.getString("author");
                date = object.getString("publishedAt");
                content = object.getString("content");
                imageUrl = object.getString("urlToImage");
                JSONObject sourceObj = object.getJSONObject("source");
                source = sourceObj.getString("name");

                Log.v(LOG_TAG,title+"\n"+content+"\n"+date+"\n"+source);

                news.add(new News(title,content,date,author,null,url,source,imageUrl));
            }

        }catch(JSONException e){
            Log.i(LOG_TAG,"Error while parsing the json result",e);
        }

        return news;
    }
}
