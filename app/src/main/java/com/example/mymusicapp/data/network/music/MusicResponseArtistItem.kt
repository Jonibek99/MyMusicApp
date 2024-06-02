package com.example.mymusicapp.data.network.music

import com.google.gson.annotations.SerializedName

data class MusicResponseArtistItem(
    @SerializedName("id")
    val artistEntryId: String,
    @SerializedName("record_id")
    val musicRecordId: String,
    @SerializedName("value")
    val artistName: String
)