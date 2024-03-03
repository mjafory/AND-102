package com.example.codepathmail

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emails: List<Email>) : RecyclerView.Adapter<EmailAdapter.ViewHolder>()
{

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        val senderTextView: TextView
        val titleTextView: TextView
        val summaryTextView: TextView
        val dateextView: TextView



        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            senderTextView = itemView.findViewById(R.id.senderTv)
            dateextView = itemView.findViewById(R.id.date_picker_actions)
            titleTextView = itemView.findViewById(R.id.titleTv)
            summaryTextView = itemView.findViewById(R.id.summaryTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.email_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val email = emails[position]
        // Set item views based on views and data model

        holder.senderTextView.text = email.sender
        holder.titleTextView.text = email.title
        holder.dateextView.text = email.summary
        holder.summaryTextView.text = email.date

        val isEmailRead = email.isRead

        if (!isEmailRead) {
            holder.senderTextView.setTypeface(holder.senderTextView.typeface, Typeface.BOLD_ITALIC)
            holder.dateextView.setTypeface(holder.dateextView.typeface, Typeface.BOLD_ITALIC)
            holder.titleTextView.setTypeface(holder.senderTextView.typeface, Typeface.BOLD_ITALIC)
            holder.summaryTextView.setTypeface(holder.senderTextView.typeface, Typeface.BOLD_ITALIC)
            // Set color for read emails
            val readColor = ContextCompat.getColor(holder.itemView.context, R.color.read_color)
            holder.senderTextView.setTextColor(readColor)
            holder.titleTextView.setTextColor(readColor)
            holder.dateextView.setTextColor(readColor)
            holder.summaryTextView.setTextColor(readColor)
        } else {
            holder.senderTextView.setTypeface(holder.senderTextView.typeface, Typeface.BOLD)
            holder.dateextView.setTypeface(holder.dateextView.typeface, Typeface.BOLD_ITALIC)
            holder.titleTextView.setTypeface(holder.senderTextView.typeface, Typeface.BOLD)
            holder.summaryTextView.setTypeface(holder.senderTextView.typeface, Typeface.BOLD)

            // Set color for read emails
            val readColor = ContextCompat.getColor(holder.itemView.context, R.color.read_color)
            holder.senderTextView.setTextColor(readColor)
            holder.titleTextView.setTextColor(readColor)
            holder.dateextView.setTextColor(readColor)
            holder.summaryTextView.setTextColor(readColor)
        }
    }

    override fun getItemCount(): Int = emails.size

}