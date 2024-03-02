package com.example.simplecounter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Grob references to view
        val numberView = findViewById<TextView>(R.id.number)
        val reset = findViewById<TextView>(R.id.reset)
//        val buttonView = findViewById<TextView>(R.id.button)
        val buttonView2 = findViewById<ImageView>(R.id.imageView)

        // 2. Create o variable to track the number of clicks
        var numberOfClicks = 0
        // 3. Set for the Button to odd

        buttonView2.setOnClickListener {

            numberOfClicks++

            // Update the number view
            numberView.text = numberOfClicks.toString()
//            buttonView.setBackgroundColor(ContextCompat.getColor(this, R.color.black))

        }


        reset.setOnClickListener {
            numberOfClicks = 0  // Reset the click count

            numberView.text = numberOfClicks.toString()

            // Set background color to red
            reset.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }



    }
}

