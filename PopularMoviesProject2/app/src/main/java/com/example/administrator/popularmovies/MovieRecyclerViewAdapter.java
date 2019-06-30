package com.example.administrator.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.popularmovies.models.Movies;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MoviesViewHolder> {
    private final Context mContext;
    private final static String IMAGE_PATH = "http://image.tmdb.org/t/p/w185//";
    private final List<Movies> mMoviesList;

    public MovieRecyclerViewAdapter(List<Movies> moviesList,Context context) {
        mContext=context;
        mMoviesList = moviesList;
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_items, viewGroup, false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int i) {
        String poster = IMAGE_PATH + mMoviesList.get(i).getMoviePoster();
        moviesViewHolder.moviePoster.setImageResource(R.drawable.ic_launcher_foreground);
       Picasso.with(mContext).load(poster)
                .error(R.drawable.not_avilable)
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(moviesViewHolder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
       final ImageView moviePoster;

        MoviesViewHolder(View itemView) {

            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_poster_recycler);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MovieDetails.class);
                    intent.putExtra("MoviesList", (Serializable) mMoviesList);
                    intent.putExtra("position", getAdapterPosition());
                    mContext.startActivity(intent);


                }
            });
        }
    }

}



