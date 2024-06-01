package com.example.mymusicapp.data.network.music

import com.google.gson.annotations.SerializedName

data class MusicResponseArtistItem(
    @SerializedName("id")
    val actorEntryId: String,  ////todo tododo
    @SerializedName("record_id")
    val movieRecordId: String,
    @SerializedName("value")
    val artistName: String
)