package com.farhad.techcrunch.api

import com.farhad.techcrunch.model.NewsObject
import retrofit2.http.GET

interface ApiInterface {

    @GET("top-headlines?sources=techcrunch&apiKey=a0fac88a9aa645bbbf02c39c2c62ed9f")
    suspend fun getTechCrunchNews(): NewsObject

}