package com.example.administrator.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.popularmovies.models.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

@SuppressWarnings("unchecked")
public class MovieDetails extends AppCompatActivity {
    private final static String IMAGE_PATH = "http://image.tmdb.org/t/p/w185//";

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.movie_details);
        TextView movieTitle = findViewById(R.id.original_Title);
        TextView userRating = findViewById(R.id.user_rating);
        TextView releaseDate = findViewById(R.id.release_date);
        TextView overView = findViewById(R.id.synopsis);
        ImageView moviePoster = findViewById(R.id.movie_poster);
        //noinspection unchecked
        @SuppressWarnings("ConstantConditions") List<Movies> moviesList = (List<Movies>) getIntent().getExtras().getSerializable("MoviesList");
        int position = getIntent().getExtras().getInt("position");
        assert moviesList != null;
        String movieTitleString = moviesList.get(position).getOriginalTitle();
        String overViewString = moviesList.get(position).getPlotSynopsis();
        String userRatingString = moviesList.get(position).getUserRating();
        String releaseDateString = moviesList.get(position).getReleaseDate();
        String moviePosterURLString = IMAGE_PATH + moviesList.get(position).getMoviePoster();
        this.setTitle(movieTitleString);
        movieTitle.setText(movieTitleString);
        userRating.setText(userRatingString);
        overView.setText(overViewString);
        releaseDate.setText(releaseDateString);
        Picasso.with(this).load(moviePosterURLString)
                .error(R.drawable.not_avilable)
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(moviePoster);
        super.onCreate(savedInstanceState);
    }
}
