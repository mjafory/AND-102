package com.example.wishlist2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.simplewishlist.WishListItem
import com.example.simplewishlist.databinding.FragmentNewWishListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewWishList(var wishItem : WishListItem?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewWishListBinding
    private lateinit var wishListModel: WishListModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        // Similartiies
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        wishListModel = ViewModelProvider(activity)[WishListModel::class.java]
        binding.submitButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {   // bidning to the View, as the Fragmentbottom got inflated
        binding = FragmentNewWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction()
    {
        // Save the data to the ViewHolder layout
        val name = binding.WishList1.text.toString()
        val price = binding.WishList2.text.toString()
        val link = binding.WishList3.text.toString()

        val newWish = WishListItem(name, price, link)

        // This is magic ngl
        wishListModel.addWishListItem(newWish)

        // Same thing, tested, clear output
        binding.WishList1.text?.clear()
        binding.WishList2.text?.clear()
        binding.WishList3.text?.clear()
        binding.WishList1.setText("")
        binding.WishList2.setText("")
        binding.WishList3.setText("")

        // reset fragments
        dismiss()

    }


}