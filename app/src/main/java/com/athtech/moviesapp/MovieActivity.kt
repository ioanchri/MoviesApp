package com.athtech.moviesapp


class MovieActivity {

    private var title: String
    private var poster: String
    private var overview: String
    private var rating: Double
    private  var release: String

    constructor(title: String, poster: String, overview: String, rating: Double, release: String) {
        this.title = title
        this.poster = poster
        this.overview = overview
        this.rating = rating
        this.release = release
    }


}