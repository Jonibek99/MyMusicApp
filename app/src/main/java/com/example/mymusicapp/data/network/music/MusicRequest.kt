package com.example.mymusicapp.data.network.music

import com.google.gson.annotations.SerializedName


data class MusicRequest(

    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val artists: List<String>,
    @SerializedName("age")
    val duration: Int,
    @SerializedName("color")
    val genre: String
)
