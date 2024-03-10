package com.example.wishlist

import android.app.AlertDialog
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class listAdapter(var lists: MutableList<Listing>) : RecyclerView.Adapter<listAdapter.ViewHolder>() {


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val itemEditText: TextView
        val urlEditText: TextView
        val priceEditText: TextView
        val editIcon: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            itemEditText = itemView.findViewById(R.id.wishName)
            urlEditText=itemView.findViewById(R.id.wishUrl)
            priceEditText=itemView.findViewById(R.id.wishPrice)
            editIcon= itemView.findViewById(R.id.editIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
        val inflater= LayoutInflater.from(context)
        val contactView=inflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = lists[position]

        holder.itemEditText.text = list.item
        holder.urlEditText.text = list.url_type
        holder.priceEditText.text = "$${list.price}"

        // Handle click event for editIcon TextView
        holder.editIcon.setOnClickListener {
            // Handle the edit option here, for example, navigate to an edit screen
            // You can pass the data for the selected item to the edit screen
            // using Intent or another navigation method

            // For demonstration purposes, let's print a log message for now
            showEditDialog(holder, list)
        }



        // Set an onClickListener for the delete button in each item
        holder.itemView.findViewById<View>(R.id.button2).setOnClickListener {
            deleteItem(holder.adapterPosition)
        }

    }


    override fun getItemCount(): Int {
        return lists.size
    }


    private fun deleteItem(position: Int) {
        // Handle the item deletion logic here
        // For example, remove the corresponding item from the data list
        lists.removeAt(position)

        // Notify the adapter that the data set has changed
        notifyDataSetChanged()
    }

    private fun showEditDialog(holder: ViewHolder, list: Listing) {
        val context = holder.itemView.context
        val editText = EditText(context)
        editText.setText(list.item)

        // Create a dialog with an EditText
        AlertDialog.Builder(context)
            .setTitle("Edit Item Name")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                // Update the item name and notify data set changed
                val newName = editText.text.toString()
                holder.itemEditText.text = newName  // Update the TextView directly in the ViewHolder
                list.item = newName
                notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}