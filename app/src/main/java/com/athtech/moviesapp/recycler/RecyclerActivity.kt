package com.athtech.moviesapp.recycler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.athtech.moviesapp.DetailActivity
import com.athtech.moviesapp.R
import com.athtech.moviesapp.json.JsonResponse
import com.google.gson.Gson



class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


    }


    override fun onPostResume() {
        super.onPostResume()


        val queue = Volley.newRequestQueue(this)
        val UrlMovies =
            //    "https://api.themoviedb.org/3/movie/top_rated?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
            "https://api.themoviedb.org/3/movie/popular?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
        val UrlPhotos = "https://themoviedb.org/t/p/w342"

        val stringRequest = StringRequest(UrlMovies,
            object : Response.Listener<String> {
                override fun onResponse(response: String?) {
                    val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)

                    var dataList = mutableListOf<ListData>()
                    jsonResponse.results.forEach {
                        val value = it.id.toString()

                    // Make a request to the second API using the value obtained from the first API
                        val UrlCasting = "https://api.themoviedb.org/3/movie/$value/credits?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
                        val secondRequest = StringRequest(UrlCasting,
                            object : Response.Listener<String> {
                                override fun onResponse(response: String?) {
                                    val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)
                                    var dataList2 = mutableListOf<ListData.ListData2>()
                                    jsonResponse.cast.forEach {
                                        var row2 = ListData.ListData2(
                                            it.original_name
                                        )
                                        dataList2.add(row2)
                                    }
                                }
                            }, object : Response.ErrorListener {
                                override fun onErrorResponse(error: VolleyError?) {
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
                            it.original_name
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
                                val intent =
                                    Intent(this@RecyclerActivity, DetailActivity::class.java)
                                intent.putExtra("title", data.movieTitle)
                                intent.putExtra("release_date", data.movieRelease)
                                intent.putExtra("overview", data.movieOverview)
                                intent.putExtra("backdrop_image", data.movieBackdrop)
                                intent.putExtra("poster_image", data.moviePoster)
                                intent.putExtra("vote_average", data.movieRating)
                                intent.putExtra("id", data.movieId)
                                intent.putExtra("original_name",data.movieCasting)

                                startActivity(intent)
                            }
                        })
                }

            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.e("API", error?.message ?: "No Internet Connection!")
                }
            })

        queue.add(stringRequest)
    }
}








