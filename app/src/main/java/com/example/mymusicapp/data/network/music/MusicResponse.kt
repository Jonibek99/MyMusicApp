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
    val artists: List<MusicResponseArtistItem>,
    @SerializedName("age")
    val duration: Int,
    @SerializedName("color")
    val genre: String
)

