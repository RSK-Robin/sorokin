package com.sorokin.gifviewer.data.network

import com.sorokin.gifviewer.data.entity.network.response.GifResponse
import com.sorokin.gifviewer.data.entity.network.response.GifsListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifApi {

    @GET("random")
    suspend fun getRandomGif(
        @Query("json") json: Boolean = true
    ): GifResponse

    @GET("{section}/{page}")
    suspend fun getGifsBySection(
        @Path("section") section: String,
        @Path("page") page: Int,
        @Query("json") json: Boolean = true
    ): GifsListResponse
}