package com.athtech.moviesapp

    class MovieActivity {

        private var title: String
        private var poster: String
        private var overview: String
        private var rating: Double
        private var release_date: String

        constructor(title: String, poster: String, overview: String, rating: Double, release_date:String) {
            this.title = title
            this.poster = poster
            this.overview = overview
            this.rating = rating
            this.release_date = release_date
        }
    }
