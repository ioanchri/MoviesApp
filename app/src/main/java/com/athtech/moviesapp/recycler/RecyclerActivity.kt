package com.athtech.moviesapp.recycler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.athtech.moviesapp.DetailActivity
import com.athtech.moviesapp.R
import com.athtech.moviesapp.json.JsonResponse
import com.athtech.moviesapp.recycler.ListData.ListData2
import com.google.gson.Gson
import androidx.appcompat.widget.SearchView
import com.athtech.moviesapp.json.JsonCasting2


class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.clearFocus()
        searchView.setOnSearchClickListener {}

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchMovies(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchMovies(newText)
                return true
            }
        })

    }

    fun searchMovies(query: String) {
        val queue = Volley.newRequestQueue(this)
        val SearchUrl =
            "https://api.themoviedb.org/3/search/movie?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US&query=$query&page=1&include_adult=false"

        val searchRequest = StringRequest(SearchUrl, { response ->
            // Parse the response and update the list of movies
            val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)
            val movies = jsonResponse.results
            // Update the adapter with the new list of movies
            // Create a new list of movies that only includes the movies that match the search query
            val filteredMovies =
                movies.filter { movie -> movie.title.contains(query, ignoreCase = true) }

        }, { error ->
            // Handle error
        })
        queue.add(searchRequest)
    }


    override fun onPostResume() {
        super.onPostResume()


        val queue = Volley.newRequestQueue(this)
        val UrlMovies =
            "https://api.themoviedb.org/3/movie/popular?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
        //  "https://api.themoviedb.org/3/movie/top_rated?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"

        val UrlPhotos = "https://themoviedb.org/t/p/w342"


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

                                dataList2.forEach { list2.add(it.movieCasting) }


                                println(" Data List 2 " + dataList2[0].movieCasting + dataList2[1].movieCasting)
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
                Log.e("API", error?.message ?: "No Internet Connection!")
                Toast.makeText(this@RecyclerActivity, "Error: ${error?.message}", Toast.LENGTH_LONG)
                    .show()
            }
        })

        queue.add(firstRequest)
    }
}








