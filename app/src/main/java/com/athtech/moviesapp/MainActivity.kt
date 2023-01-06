package com.athtech.moviesapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.athtech.moviesapp.recycler.RecyclerActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        var intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)

        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.clearFocus()
        searchView.setOnSearchClickListener {
            searchView.isIconified = false
        }


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Update the search results as the query changes
        //        searchMovies(newText)
                return true
            }

    })

    }

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