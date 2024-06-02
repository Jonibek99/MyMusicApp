package com.example.mymusicapp.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mymusicapp.data.MusicRepository
import com.example.mymusicapp.models.Music
import com.example.mymusicapp.navigation.Screens
import kotlinx.coroutines.launch

class DetailedViewModel(
    musicId: String,
    private val musicRepository: MusicRepository
) : ViewModel() {

    val musicLiveData: MutableLiveData<Music> by lazy {
        MutableLiveData<Music>()
    }

    init {
        getMusicById(musicId)
    }

    private fun getMusicById(musicId: String) {
        viewModelScope.launch {
            if (musicId.isNotEmpty()) {
                val music = musicRepository.getMusicById(musicId)
                musicLiveData.value = music
            }
        }
    }

    fun deleteMusic(music: Music, navController: NavHostController) {
        viewModelScope.launch {
            val isDeleted = musicRepository.deleteMusic(music)
            if (isDeleted) {
                navController.navigate(Screens.MusicListScreen.route) {
                    popUpTo(Screens.MusicListScreen.route) { inclusive = true }
                }
            }
        }
    }

}