package com.athtech.moviesapp.recycler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.athtech.moviesapp.DetailActivity
import com.athtech.moviesapp.R
import com.athtech.moviesapp.json.JsonEntryResponse
import com.athtech.moviesapp.json.JsonEntryResponseCasting
import com.athtech.moviesapp.json.JsonResponse
import com.athtech.moviesapp.json.JsonResponseCasting
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
        val endpoint =
            //    "https://api.themoviedb.org/3/movie/top_rated?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
            "https://api.themoviedb.org/3/movie/popular?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"
        val endpointPhotos = "https://themoviedb.org/t/p/w342"

      //  val endpointCasting =
        //    "https://api.themoviedb.org/3/movie/{${JsonEntryResponse}}/credits?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US"

        val stringRequest = StringRequest(endpoint,
            object : Response.Listener<String> {
                override fun onResponse(response: String?) {
                    val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)
                    val jsonResponseCasting = Gson().fromJson(response, JsonResponseCasting::class.java)
                    var dataList2 = mutableListOf<ListData.ListData2>()

 //                   val movieID = ListData.movieId

                    jsonResponseCasting.cast.forEach{
                        var row2 = ListData.ListData2(
                            it.original_name
                        )
                        dataList2.add(row2)
                    }

                    var dataList = mutableListOf<ListData>()
                    jsonResponse.results.forEach {
                        var row = ListData(
                            it.title,
                            it.release_date,
                            endpointPhotos + it.backdrop_path,
                            it.vote_average.toString(),
                            it.overview,
                            endpointPhotos + it.poster_path,
                            it.id.toString()
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
        //                        intent.putExtra("casting", movieID.toString())

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