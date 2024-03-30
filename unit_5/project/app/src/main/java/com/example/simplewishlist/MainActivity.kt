package com.example.simplewishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.simplewishlist.R
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.simplewishlist.databinding.ActivityMainBinding as ActivityMainBinding1

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding1
    private lateinit var wishListViewModel: WishListModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        // Create ActivityMainBiding
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding1.inflate(layoutInflater)
        setContentView(binding.root)
        wishListViewModel = ViewModelProvider(this)[WishListModel::class.java]

        // Everytime we click on the submitButton, we will have a new Wishlist,add in the RvView
        binding.submitMain.setOnClickListener {
            NewWishList(null).show(supportFragmentManager, "NewWishList")
        }

        // After receiving the WishList data fetched, set it via adapter
        setRecyclerView()


        findViewById<Button>(R.id.resetButton).setOnClickListener {
            findViewById<TextView>(R.id.nameItem).text = ""  // Clear text for nameItem
            findViewById<TextView>(R.id.caloriesItem).text = ""  // Clear text for caloriesItem
            findViewById<TextView>(R.id.timeItem).text = ""  // Clear text for timeItem
        }

    }

    private fun setRecyclerView()
    {
        val mainActivity = this

        wishListViewModel.wishList.observe(this){
            binding.rvWishList.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = WishListAdapter(it)
            }
        }
    }
}