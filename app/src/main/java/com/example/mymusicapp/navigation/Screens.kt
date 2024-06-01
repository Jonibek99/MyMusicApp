package com.example.mymusicapp.navigation

sealed class Screens(val route: String) {
    object MusicListScreen: Screens("music_list")
}
