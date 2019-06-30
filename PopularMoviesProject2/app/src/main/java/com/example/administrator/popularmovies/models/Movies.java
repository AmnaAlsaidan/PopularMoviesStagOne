package com.example.administrator.popularmovies.models;

import java.io.Serializable;

/*original title
        movie poster image thumbnail
        A plot synopsis (called overview in the api)
        user rating (called vote_average in the api)
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        release d*/
public class Movies  implements Serializable{
    public Movies(String originalTitle, String moviePoster, String userRating, String  releaseDate, String  plotSynopsis) {
        this.originalTitle = originalTitle;
        this.moviePoster = moviePoster;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    private final String originalTitle;
    private final String moviePoster;
    private final String plotSynopsis;
    private final String userRating;
    private final String releaseDate;


}
