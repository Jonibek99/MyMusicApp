package com.example.mymusicapp.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicapp.data.MusicRepository
import com.example.mymusicapp.data.network.response.MyResponse
import com.example.mymusicapp.models.Music
import kotlinx.coroutines.launch

class AddNewMusicViewModel(private val musicRepository: MusicRepository) : ViewModel() {

    val insertResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewMusic(music: Music) {
        viewModelScope.launch {
            try {

                val response = musicRepository.insertNewMusic(music)
                insertResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}