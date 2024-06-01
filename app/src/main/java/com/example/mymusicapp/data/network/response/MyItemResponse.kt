package com.example.mymusicapp.data.network.response

import com.google.gson.annotations.SerializedName

class MyItemResponse<T> (@SerializedName("data")
                         val data: T?) : MyResponse()
