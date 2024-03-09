package com.example.wishlist2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wishlist2.WishListItem

class WishListModel: ViewModel()
{
    var wishList = MutableLiveData<MutableList<WishListItem>>()

    init {
        wishList.value = mutableListOf()
    }

    fun addWishListItem(wishListItem: WishListItem)
    {
        wishList.value?.add(wishListItem)
        val list = wishList.value
        wishList.postValue(list)
    }
}
