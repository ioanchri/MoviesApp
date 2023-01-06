package com.athtech.moviesapp.recycler

class ListData {
    val movieTitle: String
    val movieRelease: String
    val movieBackdrop: String
    val movieRating: String
    val movieOverview: String
    val moviePoster: String
    val movieId: String
    val movieCasting: String



    constructor(
        movieTitle: String,
        movieRelease: String,
        movieBackdrop: String,
        movieRating: String,
        movieOverview: String,
        moviePoster: String,
        movieId: String,
        movieCasting: String
    ) {
        this.movieTitle = movieTitle
        this.movieRelease = movieRelease
        this.movieBackdrop = movieBackdrop
        this.movieRating = movieRating
        this.movieOverview = movieOverview
        this.moviePoster = moviePoster
        this.movieId = movieId
        this.movieCasting = movieCasting
     }

    class ListData2 {
        val movieCasting: String


        constructor(movieCasting: String){
        this.movieCasting = movieCasting}
    }




}