package com.example.wishlist

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val three_list= mutableListOf<Listing>()
private lateinit var rvList : RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize
        val rvList = findViewById<RecyclerView>(R.id.recyclerView)
        val wishAdp = listAdapter(three_list)

        //create layout manager
        rvList.adapter= wishAdp
        rvList.layoutManager=LinearLayoutManager(this)


        val wishItemname = findViewById<EditText>(R.id.name)
        val wishPricenum = findViewById<EditText>(R.id.price)
        val wishUrlsearch = findViewById<EditText>(R.id.url)

        findViewById<Button>(R.id.button).setOnClickListener {
            var item : Listing = Listing(wishItemname.text.toString(), wishUrlsearch.text.toString(), wishPricenum.text.toString().toDouble())
            three_list.add(item)
            findViewById<EditText>(R.id.price).text.clear()
            findViewById<EditText>(R.id.name).text.clear()
            findViewById<EditText>(R.id.url).text.clear()
            wishAdp.notifyDataSetChanged()
        }
    }


}