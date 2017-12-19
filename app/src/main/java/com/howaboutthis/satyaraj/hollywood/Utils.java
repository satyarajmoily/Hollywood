package com.howaboutthis.satyaraj.hollywood;

import android.text.TextUtils;
import android.util.Log;
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
import java.util.Objects;

class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();
    private static String mShow;


    static List<ItemEvent> fetchNewsData(String requestUrl, String show) {
        List<ItemEvent> mListItem;
        URL url = createUrl(requestUrl);

        mShow = show;

        mListItem = new ArrayList<>();

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Error closing input stream",e);
        }

        return extractFeatureFromJson(jsonResponse,mListItem);

    }

    private static List<ItemEvent>extractFeatureFromJson(String jsonResponse, List<ItemEvent> mListItem) {
        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        try{
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            int mPageNumber = baseJsonResponse.getInt("page");
            int totalPages = baseJsonResponse.getInt("total_pages");
            JSONArray resultsArray = baseJsonResponse.getJSONArray("results");
            ItemEvent mlistItem;

            JSONObject object;
            long id;
            String title;
            String description;
            double rating;
            String year;
            String urlToImage;
            JSONArray genre;
            double popularity;
            long votingCount;
            String backdrop;



            if(Objects.equals(mShow, "movie")) {

                for (int i = 0; i < resultsArray.length(); i++) {
                    object = resultsArray.getJSONObject(i);

                    id = object.getLong("id");
                    title = object.getString("title");
                    description = object.getString("overview");
                    rating = object.getDouble("vote_average");
                    year = object.getString("release_date");
                    urlToImage = object.getString("poster_path");
                    urlToImage = "https://image.tmdb.org/t/p/w500" + urlToImage;
                    genre = object.getJSONArray("genre_ids");
                    popularity = object.getDouble("popularity");
                    votingCount = object.getLong("vote_count");
                    backdrop = object.getString("backdrop_path");
                    backdrop = "https://image.tmdb.org/t/p/w500" + backdrop;


                    mlistItem = new ItemEvent(id, title, description, genre, rating, urlToImage, year, popularity, votingCount, backdrop, mPageNumber,totalPages);

                    mListItem.add(mlistItem);

                }
            }
                else if (Objects.equals(mShow, "tv"))
                {

                    for (int i = 0; i < resultsArray.length(); i++) {
                        object = resultsArray.getJSONObject(i);

                        id = object.getLong("id");
                        title = object.getString("original_name");
                        description = object.getString("overview");
                        rating = object.getDouble("vote_average");
                        year = object.getString("first_air_date");
                        urlToImage = object.getString("poster_path");
                        urlToImage = "https://image.tmdb.org/t/p/w500" + urlToImage;
                        genre = object.getJSONArray("genre_ids");
                        popularity = object.getDouble("popularity");
                        votingCount = object.getLong("vote_count");
                        backdrop = object.getString("backdrop_path");
                        backdrop = "https://image.tmdb.org/t/p/w500" + backdrop;

                        mlistItem = new ItemEvent(id, title, description, genre, rating, urlToImage, year, popularity, votingCount, backdrop,mPageNumber, totalPages);

                        mListItem.add(mlistItem);

                    }

                }


            return mListItem;

        } catch (JSONException e) {
            Log.e(LOG_TAG,"Problem parsing the News JSON results",e);
        }
        return null;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse ="";

        if(url ==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream= null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG,"Error response code: "+ urlConnection.getResponseCode());
            }
        } catch (IOException e) {
           Log.e(LOG_TAG,"Problem retrieving the News JSON results.",e);
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line !=null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try{
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Error with creating URL", e);
        }
        return url;
    }

}
