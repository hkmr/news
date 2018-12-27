package com.example.harshkumar.mynews.utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.harshkumar.mynews.data.NewsSource;

import org.json.JSONArray;
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

public class QueryUtilsForSource {

    private static final String LOG_TAG = QueryUtilsForSource.class.getSimpleName();

    private QueryUtilsForSource(){}

    public static List<NewsSource> fetchData(String requestUrl){

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem in making the HTTP request",e);
        }

        List<NewsSource> sources = extractFeatureFromJson(jsonResponse);

        return sources;
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

    private static List<NewsSource> extractFeatureFromJson(String jsonResponse){

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<NewsSource> newsSources = new ArrayList<>();

        try{

            JSONObject mainObject = new JSONObject(jsonResponse);
            JSONArray sources = mainObject.getJSONArray("sources");

            for (int i=0;i<sources.length();i++){

                JSONObject currentObj = sources.getJSONObject(i);
                String id = currentObj.getString("id");
                String name = currentObj.getString("name");
                String desc = currentObj.getString("description");
                String url = currentObj.getString("url");
                String category = currentObj.getString("category");
                String langId = currentObj.getString("language");
                String countryId = currentObj.getString("country");

                newsSources.add(new NewsSource(id,name,desc,url,category,
                        langId,countryId,"http://square.github.io/okhttp/static/icon-github.png"));

            }


        }catch (Exception e){
            Log.i(LOG_TAG,"Error while parsing the json result",e);
        }

        return newsSources;
    }

}
