package com.example.mymusicapp.models


data class Music(
    val id: String = "",
    val title: String,
    val description: String,
    val founders: List<String>,
    val gender: String,
    val duration: Int
    )
