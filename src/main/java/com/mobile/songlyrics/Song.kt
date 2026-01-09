package com.mobile.songlyrics

data class Song(
    val band: String,
    val title: String,
    val lyrics: String
)

data class LyricsResponse(
    val lyrics: String
)