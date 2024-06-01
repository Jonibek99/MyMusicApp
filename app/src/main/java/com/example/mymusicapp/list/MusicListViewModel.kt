package com.example.mymusicapp.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicapp.data.MusicRepository
import com.example.mymusicapp.models.Music
import kotlinx.coroutines.launch

data class MusicListViewModel(
    private val musicRepository: MusicRepository) : ViewModel() {
    val musicLiveData: MutableLiveData<List<Music>> by lazy { MutableLiveData<List<Music>>()
    }

    init {
        getAllMusic()
    }

    fun getAllMusic() {
        viewModelScope.launch {
            val music = musicRepository.getMusicList()
            musicLiveData.value = music
        }
    }
}
