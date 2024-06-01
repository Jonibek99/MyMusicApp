package com.example.mymusicapp.data.network.music

import com.google.gson.annotations.SerializedName

data class MusicResponseFounderItem(
    @SerializedName("id")
    val actorEntryId: String,
    @SerializedName("record_id")
    val movieRecordId: String,
    @SerializedName("value")
    val founderName: String
)