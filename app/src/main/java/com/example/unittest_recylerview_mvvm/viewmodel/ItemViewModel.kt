package com.example.unittest_recylerview_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {

    private val _items = MutableLiveData<List<String>>()
    val items: LiveData<List<String>>
        get() = _items

    init {
        // Initialize the list with some dummy data
        _items.value = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    }
}

