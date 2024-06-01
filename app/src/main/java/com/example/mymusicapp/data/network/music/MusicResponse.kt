package com.example.mymusicapp.data.network.music

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val founders: List<MusicResponseFounderItem>,
    @SerializedName("price")
    val price: Double,
    @SerializedName("age")
    val size: Int,
    @SerializedName("color")
    val gender: String
)

