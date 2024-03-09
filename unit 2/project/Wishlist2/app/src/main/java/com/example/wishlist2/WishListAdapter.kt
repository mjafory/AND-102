package com.example.wishlist2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist2.WishListItem


// Creating class WishListItemViewHolder separately
// as intention to see Kotlin class link with each other
class WishListAdapter(
    // Adapter will have array of wishes
    private val wishes: MutableList<WishListItem>,
) : RecyclerView.Adapter<WishListItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val wishView = WishlistItemsBinding.inflate(inflater, parent, false)

        // Return a new holder instance
        return WishListItemViewHolder(context, wishView)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: WishListItemViewHolder, position: Int) {
        // Get the data model based on position
        holder.bindWishListItem(wishes[position])

        // Thank you, random stranger on the internet, this set the the itemView onCLickListener
        holder.itemView.setOnClickListener(View.OnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wishes[position].url))
                ContextCompat.startActivity(holder.itemView.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(holder.itemView.context, "Invalid URL for ${wishes[position].url}", Toast.LENGTH_LONG).show()
            }
        })

        // Format so they won't look too ugly
        holder.nameTextView.setTypeface(holder.nameTextView.typeface, Typeface.BOLD_ITALIC)
        holder.priceTextView.setTypeface(holder.priceTextView.typeface, Typeface.BOLD)
        holder.urlTextView.setTypeface(holder.urlTextView.typeface, Typeface.BOLD_ITALIC)
    }

    override fun getItemCount(): Int = wishes.size
}

