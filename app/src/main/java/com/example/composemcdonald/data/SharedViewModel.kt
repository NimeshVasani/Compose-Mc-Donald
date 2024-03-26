package com.example.composemcdonald.data

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.composemcdonald.model.MenuItem

class SharedViewModel : ViewModel() {
    private val _selectedItems = mutableStateListOf<MenuItem>()
    val selectedItems: List<MenuItem>
        get() = _selectedItems

    fun addSelectedItem(item: MenuItem) {
        _selectedItems.add(item)
    }

    fun removeSelectedItem(item: MenuItem) {
        _selectedItems.remove(item)
    }
}
