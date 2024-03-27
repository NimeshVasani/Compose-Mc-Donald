package com.example.composemcdonald.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuItem(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val categoryId: Long,
    var quantity: Int,
    var contains : String = "Slices of raw onion, lettuce, bacon, mayonnaise, and other ingredients add flavor"
) : Parcelable