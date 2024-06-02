package com.example.mymusicapp.data.network

import com.example.mymusicapp.data.network.response.MyItemResponse
import com.example.mymusicapp.data.network.response.MyListResponse
import com.example.mymusicapp.data.network.response.MyResponse
import com.example.mymusicapp.data.network.music.MusicRequest
import com.example.mymusicapp.data.network.music.MusicResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
// postman
interface MusicService {
    @GET("records/all")
    suspend fun getAllMusic(@Query("student_id") student_id: String):
            MyListResponse<MusicResponse>

    @POST("records")
    suspend fun insertNewMusic(
        @Query("student_id") student_id: String,
        @Body musicRequest: MusicRequest
    ): MyResponse

    @GET("records/{record_id}")
    suspend fun getOneMusicById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<MusicResponse>

    @DELETE("records/{record_id}")
    suspend fun deleteMusic(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): Response<Void>

}