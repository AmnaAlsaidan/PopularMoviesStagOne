package com.example.administrator.popularmovies;

import com.example.administrator.popularmovies.models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonUtils {

    public static List<Movies> parseMovieJson(String json) throws JSONException {
        JSONObject movieObject = new JSONObject(json);
        JSONArray results = movieObject.getJSONArray("results");
        List<Movies> moviesList=new ArrayList<>();

        for (int i = 0; i < results.length(); ++i) {
            moviesList.add(new Movies(results.getJSONObject(i).getString("title"),
                    results.getJSONObject(i).getString("poster_path"),
                    results.getJSONObject(i).getString("vote_average"),
                    results.getJSONObject(i).getString("release_date"),
                    results.getJSONObject(i).getString("overview")));
        }
        return moviesList;
    }
}
