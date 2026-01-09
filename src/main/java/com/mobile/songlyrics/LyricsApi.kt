package com.mobile.songlyrics

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface LyricsApi {

    @GET
    suspend fun getLyrics(
        @Url url: String
    ): LyricsResponse
}