package com.example.mymusicapp.data

import android.util.Log
import com.example.mymusicapp.data.network.RetrofitInstance
import com.example.mymusicapp.data.network.response.MyItemResponse
import com.example.mymusicapp.data.network.response.MyListResponse
import com.example.mymusicapp.data.network.response.MyResponse
import com.example.mymusicapp.data.network.music.MusicRequest
import com.example.mymusicapp.data.network.music.MusicResponse
import com.example.mymusicapp.data.network.music.MusicResponseArtistItem
import com.example.mymusicapp.models.Music

class MusicRepository {
    suspend fun getMusicList(): List<Music> {
        val music = mutableListOf<Music>()

        try {

            val response: MyListResponse<MusicResponse> =
                RetrofitInstance.musicService.getAllMusic("09974")
            val musicsFromResponse = response.data

            if (musicsFromResponse != null) {
                for (musicFromResponse in musicsFromResponse) {
                    if (musicFromResponse.description != null) {
                        music.add(
                            Music(
                                id = musicFromResponse.id.toString(),
                                title = musicFromResponse.name.uppercase(),
                                description = musicFromResponse.description.toString(),
                                artists = musicFromResponse.artists as List<String>,
                                genre = musicFromResponse.genre,
                                duration = musicFromResponse.duration,
                            )
                        )
                    }
                }

            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return music
    }

    suspend fun insertNewMusic(music: Music): MyResponse? {
        val response: MyResponse

        try {
            val musicRequest =
                MusicRequest(
                    title = music.title,
                    description = music.description,
                    artists = music.artists,
                    genre = music.genre,
                    duration = music.duration
                )

            response = RetrofitInstance.musicService.insertNewMusic(
                "09974",
                musicRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }

    suspend fun getMusicById(musicId: String): Music? {
        try {
            val response: MyItemResponse<MusicResponse> =
                RetrofitInstance.musicService.getOneMusicById(musicId, "09974")
            val musicFromResponse = response.data

            if (musicFromResponse != null) {
                if (musicFromResponse.description != null
                ) {
                    return Music(
                        id = musicId,
                        title = musicFromResponse.name,
                        description = musicFromResponse.description,
                        artists = extractListOfArtistsFromResponse(musicFromResponse.artists),
                        genre = musicFromResponse.genre,
                        duration = musicFromResponse.duration
                    )
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    private fun extractListOfArtistsFromResponse(
        artistsFromResponse: List<MusicResponseArtistItem>
    ): List<String> {

        val myArtists = mutableListOf<String>()

        for (artistObj in artistsFromResponse) {
            myArtists.add(artistObj.artistName)
        }

        return myArtists
    }

    suspend fun deleteMusic(music: Music): Boolean {
        return try {
            val response = RetrofitInstance.musicService.deleteMusic(
                music.id,
                "09974"
            )
            if (response.isSuccessful) {
                Log.d("Delete_response", "Music deleted successfully")
                true
            } else {
                Log.e("Delete_response", "Failed to delete music")
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
