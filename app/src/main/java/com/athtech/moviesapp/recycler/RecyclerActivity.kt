package com.athtech.moviesapp.recycler

import android.content.Intent
import android.graphics.Color.BLACK
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.athtech.moviesapp.DetailActivity
import com.athtech.moviesapp.R
import com.athtech.moviesapp.json.JsonCasting2
import com.athtech.moviesapp.json.JsonResponse
import com.athtech.moviesapp.recycler.ListData.ListData2
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson


class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val actionBar: ActionBar? = supportActionBar

        if (actionBar != null) {
            actionBar.hide()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                searchMovies(newText)

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchMovies(query)
                return true
            }
        })

    }

    fun searchMovies(query: String) {

//        val queue = Volley.newRequestQueue(this)
//        val SearchUrl =
//            "https://api.themoviedb.org/3/search/movie?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US&query=$query&page=1&include_adult=false"


        val snackbar = Snackbar.make(
            findViewById(R.id.recyclerView),
            "We are sorry but the Search function isn't implemented yet. You searched for: $query",
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(BLACK)
        snackbar.show()


//        val searchRequest = StringRequest(SearchUrl, { response ->
//            val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)
//            val movies = jsonResponse.results
//            // Create a new list of movies that only includes the movies that match the search query
//
//            val filteredListData = movies.map { movie ->
//
//                // Convert the Movie object to a ListData object
//                val mutableList = mutableListOf<String>()
//                ListData(
//                    movieTitle = movie.title,
//                    movieId = movie.id.toString(),
//                    movieOverview = movie.overview,
//                    movieRelease = movie.release_date,
//                    movieRating = movie.vote_average.toString(),
//                    movieBackdrop = movie.backdrop_path,
//                    moviePoster = movie.poster_path,
//                    movieCasting = mutableList
//                )
//            }.filter { listData ->
//                listData.movieTitle.contains(query, ignoreCase = true)
//            }
//        }, { error ->
//            Toast.makeText(this@RecyclerActivity, "Error: ${error?.message}", Toast.LENGTH_LONG)
//                .show()        })
//        queue.add(searchRequest)
    }


    override fun onPostResume() {
        super.onPostResume()
        val queue = Volley.newRequestQueue(this)


        val UrlMovies =
            "https://api.themoviedb.org/3/movie/popular?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"

        val UrlPhotos = "https://themoviedb.org/t/p/w342"

        // Make a request to the first API to get the most popular movies

        val firstRequest = StringRequest(UrlMovies, object : Response.Listener<String> {


            override fun onResponse(response: String?) {
                val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)

                var dataList = mutableListOf<ListData>()
                jsonResponse.results.forEach {
                    var list2 = mutableListOf<String>()
                    val movieIdentification = it.id.toString()

                    // Make a request to the second API using the value obtained from the first API
                    val UrlCasting =
                        "https://api.themoviedb.org/3/movie/$movieIdentification/credits?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
                    val secondRequest =
                        StringRequest(UrlCasting, object : Response.Listener<String> {
                            override fun onResponse(response: String?) {
                                val JsonCasting2 =
                                    Gson().fromJson(response, JsonCasting2::class.java)
                                var dataList2 = mutableListOf<ListData2>()
                                JsonCasting2.cast.forEach {
                                    var row2 = ListData2(
                                        it.original_name
                                    )
                                    dataList2.add(row2)
                                }
                                //Add the casting actors of each movie in a list

                                dataList2.forEach { list2.add(it.movieCasting) }
                            }
                        }, object : Response.ErrorListener {
                            override fun onErrorResponse(error: VolleyError?) {
                                Toast.makeText(
                                    this@RecyclerActivity,
                                    "Error: ${error?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                Log.e("API", error?.message ?: "No Internet Connection!")
                            }
                        })

                    queue.add(secondRequest)

                    var row = ListData(
                        it.title,
                        it.release_date,
                        UrlPhotos + it.backdrop_path,
                        it.vote_average.toString(),
                        it.overview,
                        UrlPhotos + it.poster_path,
                        it.id.toString(),
                        list2
                    )
                    dataList.add(row)

                }

                var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter = RecyclerAdapterWithListData(
                    this@RecyclerActivity,
                    dataList,
                    object : OnItemClickListener {
                        override fun onClick(data: ListData) {
                            Log.d("Activity-Holder", "We recieved Data")
                            val intent = Intent(this@RecyclerActivity, DetailActivity::class.java)
                            intent.putExtra("title", data.movieTitle)
                            intent.putExtra("release_date", data.movieRelease)
                            intent.putExtra("overview", data.movieOverview)
                            intent.putExtra("backdrop_image", data.movieBackdrop)
                            intent.putExtra("poster_image", data.moviePoster)
                            intent.putExtra("vote_average", data.movieRating)
                            intent.putExtra("id", data.movieId)
                            intent.putExtra("original_name", data.movieCasting.toString())

                            startActivity(intent)
                        }


                    })
            }

        }, object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@RecyclerActivity, "Error: ${error?.message}", Toast.LENGTH_LONG)
                    .show()
            }
        })

        queue.add(firstRequest)
    }
}








