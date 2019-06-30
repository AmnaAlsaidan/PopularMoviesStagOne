package com.example.administrator.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.administrator.popularmovies.models.Movies;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int numberOfColumns;
    private ProgressBar progressBar;
    private final static String API_KEY = "api_key=ce4bc4732cb010a2aeedb4eaf55e9557";
    private final static String MovieDB_POPULAR_URL = "https://api.themoviedb.org/3/movie/popular?" + API_KEY;
    private final static String MovieDB_TOP_RATED_URL = "https://api.themoviedb.org/3/movie/top_rated?" + API_KEY;
    private List<Movies> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        numberOfColumns = 2;
        recyclerView = findViewById(R.id.recyclerView);
        progressBar.setVisibility(View.VISIBLE);
        Log.i("MovieDB_POPULAR_URL",""+MovieDB_POPULAR_URL);
        progressBar.setVisibility(View.VISIBLE);

        loadMovieURL(MovieDB_POPULAR_URL);
    }

    private void loadMovieURL(String movieURL) {
        new MovieDBTask().execute(movieURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.highest_rated) {
            this.setTitle(R.string.highest_rated);

            loadMovieURL(MovieDB_TOP_RATED_URL);

            return true;
        }
        if (id == R.id.most_popular) {
            loadMovieURL(MovieDB_POPULAR_URL);
            this.setTitle(R.string.most_popular);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class  MovieDBTask extends AsyncTask<String, Void, List<Movies>> {


        @Override
        protected List<Movies> doInBackground(String... strings) {
            String url = strings[0];
            try {
                // https://api.themoviedb.org/3/movie/popular?api_key=ce4bc4732cb010a2aeedb4eaf55e9557
                String json = NetworkUtils.getResponseFromHttpUrl(url);
                moviesList = JsonUtils.parseMovieJson(json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return moviesList;
        }


        @Override
        protected void onPostExecute(List<Movies> movies) {
            progressBar.setVisibility(View.INVISIBLE);

            super.onPostExecute(movies);
            populateUI();
        }
    }

    private void populateUI() {
        MovieRecyclerViewAdapter movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(moviesList, this);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
    }
}
