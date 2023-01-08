package com.athtech.moviesapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.athtech.moviesapp.json.JsonResponse
import com.athtech.moviesapp.recycler.RecyclerActivity
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        var intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)

//        val searchView = findViewById<SearchView>(R.id.search_view)
//        searchView.clearFocus()
//        searchView.setOnSearchClickListener {
//            searchView.isIconified = false
//        }
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                searchMovies(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                searchMovies(newText)
//                return true
//            }
//        })
    }

//    fun searchMovies(query: String) {
//        val queue = Volley.newRequestQueue(this)
//        val SearchUrl = "https://api.themoviedb.org/3/search/movie?api_key=3e7ab9723e9ad4ef5a4424fb8dbdc2d7&language=en-US&query=$query&page=1&include_adult=false"
//
//        val searchRequest = StringRequest(SearchUrl,
//            Response.Listener<String> { response ->
//                // Parse the response and update the list of movies
//                val jsonResponse = Gson().fromJson(response, JsonResponse::class.java)
//                val movies = jsonResponse.results
//                // Update the adapter with the new list of movies
//            },
//            Response.ErrorListener { error ->
//                // Handle error
//            }
//        )
//        queue.add(searchRequest)
//    }


//    private fun searchMovies(text: String) {
//      //  private fun filter(text: String) {
//            // creating a new array list to filter our data.
//            val filteredlist: ArrayList<CourseRVModal> = ArrayList()
//
//            // running a for loop to compare elements.
//            for (item in dataList) {
//                // checking if the entered string matched with any item of our recycler view.
//                if (item.courseName.toLowerCase().contains(text.toLowerCase())) {
//                    // if the item is matched we are
//                    // adding it to our filtered list.
//                    filteredlist.add(item)
//                }
//            }
//            if (filteredlist.isEmpty()) {
//                // if no item is added in filtered list we are
//                // displaying a toast message as no data found.
//                Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
//            } else {
//                // at last we are passing that filtered
//                // list to our adapter class.
//                courseRVAdapter.filterList(filteredlist)
//            }
//        }
 //   }


}