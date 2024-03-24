package com.codepath.bestsellerlistapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.w3c.dom.Text

private val getImgURL = "https://image.tmdb.org/t/p/w500"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class DetailActivity : AppCompatActivity() {
    private lateinit var receivedMovie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mMovieTitle = findViewById<TextView>(R.id.textMovieTitle)
        val mMovieOverview = findViewById<TextView>(R.id.textOverview)
        val mMovieRating = findViewById<TextView>(R.id.textRating)
        val mMovieRelease = findViewById<TextView>(R.id.textReleaseDatePlz)
        val mMovieGenre = findViewById<TextView>(R.id.textGenre)
        val mMovieImage  =findViewById<View>(R.id.movie_image)
        val mMovieProvider = findViewById<TextView>(R.id.provider)
        val mMovieBudget = findViewById<TextView>(R.id.budget)

        val intent = intent

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY


        // Get the JSON string from the intent
        val movieJson = intent.getStringExtra(MOVIE_Add)
        val mGoBack = findViewById<ImageView>(R.id.goBack)


        // Convert the JSON string back to a LatestMovies object using Gson
        val gson = Gson()
        receivedMovie = gson.fromJson(movieJson, Movie::class.java)
        val movie_id = receivedMovie.id.toString()

        mMovieTitle.text = receivedMovie.title
        mMovieOverview.text = receivedMovie.overview



//"https://api.themoviedb.org/3/movie/${movie_id}/credits",
        client[
            "https://api.themoviedb.org/3/movie/${movie_id}",
            params,
            object : JsonHttpResponseHandler() { //connect these callbacks to your API call
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    // The wait for a response is over
                    Log.v("API CALL", json.toString())
                    mMovieProvider.text = json.jsonObject.get("popularity").toString()
                    mMovieBudget.text = json.jsonObject.get("vote_count").toString()
                    mMovieRating.text = json.jsonObject.get("vote_average").toString()
                    mMovieRelease.text = json.jsonObject.get("release_date").toString()
                    val genre = json.jsonObject.get("genres") as JSONArray
                    var boi = ""
                    for (i in 0 until genre.length()) {
                        val genreObject = genre.getJSONObject(i)
                        val genreName = genreObject.getString("name")
                        // Do something with genreName
                        boi += "$genreName, "
                    }

                    // Load and display the movie image using Glide
                    val posterPath = json.jsonObject.optString("poster_path")
                    if (posterPath.isNotEmpty()) {
                        val imageURL = "$getImgURL$posterPath"
                        Glide.with(this@DetailActivity)
                            .load(imageURL)
                            .centerInside()
                            .into(mMovieImage as ImageView) // Corrected this line
                    } else {
                        // If the poster path is empty, set a placeholder image or handle it accordingly
//                        mMovieImage.setImageResource(R.drawable.placeholder_image)
                    }

                    mMovieGenre.text = boi

                    // Look for this in Logcat:
                    Log.d("MoviesFragment", "response successful")
                }


                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    Log.v("FAIL", "Doesn't work")

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("MoviesFragment", errorResponse)
                    }
                }
            }
        ]





//        Log.v("EEDE", receivedMovie.toString())
//        Log.v("EEDE", receivedMovie.title.toString())

//        mMovieOverview.text = receivedMovie.overview.toString()
//        mMovieTitle.text = receivedMovie.title.toString()

        mGoBack.setOnClickListener {
            onBackPressed()
        }
    }

}