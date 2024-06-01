package com.example.mymusicapp.models


data class Music(
    val id: String = "",
    val title: String,
    val description: String,
    val artists: List<String>,
    val genre: String,
    val duration: Int
    )
